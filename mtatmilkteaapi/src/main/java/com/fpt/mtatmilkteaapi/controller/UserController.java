package com.fpt.mtatmilkteaapi.controller;

import com.fpt.mtatmilkteaapi.config.ERole;
import com.fpt.mtatmilkteaapi.entity.Product;
import com.fpt.mtatmilkteaapi.entity.Role;
import com.fpt.mtatmilkteaapi.entity.User;
import com.fpt.mtatmilkteaapi.entity.Wishlist;
import com.fpt.mtatmilkteaapi.payload.request.ProfileRequest;
import com.fpt.mtatmilkteaapi.payload.request.UserRequest;
import com.fpt.mtatmilkteaapi.payload.response.MessageResponse;
import com.fpt.mtatmilkteaapi.payload.response.UserReponse;
import com.fpt.mtatmilkteaapi.payload.response.WishlistResponse;
import com.fpt.mtatmilkteaapi.repository.IProductRepository;
import com.fpt.mtatmilkteaapi.repository.IRoleRepository;
import com.fpt.mtatmilkteaapi.repository.IUserRepository;
import com.fpt.mtatmilkteaapi.repository.IWishlistRepository;
import com.fpt.mtatmilkteaapi.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private IWishlistRepository wishlistRepository;

    @Autowired
    private IProductRepository productRepository;

    @Value("${javadocfast.cloudinary.folder.avatar}")
    private String avatar;

    private Map<String, String> options = new HashMap<>();

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String keyword
    ) {

        Pageable pageable = PageRequest.of(
                page - 1, pageSize,
                "asc".equals(sortDir) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );

        Page<User> users = "".equals(keyword) ?
                userRepository.findUsersByUsernameNotLike("admin", pageable) :
                userRepository.findUsersByUsernameNotLikeAndFullNameLike("admin", "%" + keyword + "%", pageable);

        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @ModelAttribute UserRequest userRequest) throws IOException {

        // Check Exits Username
        if (!userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unable to identify account"));
        }

        // Find User By Username
        User user = userRepository.findByUsername(userRequest.getUsername()).get();

        // Set Propeties To Entity User To Update
        user.setFullName(userRequest.getFullName());
        user.setBirthday(userRequest.getBirthday());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());

        // Set Roles To Entity User To Update
        Set<String> strRoles = userRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.size() == 0) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }

        user.setRoles(roles);


        // Check Image Avatar
        MultipartFile multipartFile = userRequest.getAvatar();

        if (multipartFile != null) {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Invalid image"));
            }

            // Folder To Save Avatar
            options.put("folder", avatar);

            if (user.getNameImage() != null) {
                // Delete Old Avatar
                cloudinaryService.delete(user.getNameImage(), options);
            }

            // Update New Avatar
            Map result = cloudinaryService.upload(multipartFile, options);
            user.setLinkImage(result.get("url").toString());
            user.setNameImage(result.get("public_id").toString());
        }

        // Update row of table User in Database
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Data update successful"));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){

        UserReponse userReponse = new UserReponse();
        WishlistResponse wishlistResponse = new WishlistResponse();

        User user = userRepository.findByUsername(username).get();

        // Get all wishlist
        List<Wishlist> wishlists = wishlistRepository.findAllByUserId(user.getId());
        List<Product> products = new ArrayList<>();

        for(Wishlist wl : wishlists) {
            products.add(productRepository.findById(wl.getProductId()).get());
        }


        wishlistResponse.setProducts(products);
        wishlistResponse.setQuantity(products.size());
        userReponse.setUser(user);
        userReponse.setWishlistResponse(wishlistResponse);


        return ResponseEntity.ok(userReponse);
    }

    @PutMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@RequestBody UserRequest userRequest) {

        // Find user by username
        User user = userRepository.findByUsername(userRequest.getUsername()).get();

        // Get status request
        String status = userRequest.getStatus();

        // Check status and process
        if("delete".equals(status)) {
            user.setDeletedAt(new Date());
        } else if("replay".equals(status)) {
            user.setDeletedAt(null);
        }
        userRepository.save(user);

        return  ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/updateProfile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProfile(@Valid @ModelAttribute ProfileRequest profileRequest) throws IOException {
// Check Exits Username
        if (!userRepository.existsByUsername(profileRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unable to identify account"));
        }

        // Find User By Username
        User user = userRepository.findByUsername(profileRequest.getUsername()).get();

        // Set Propeties To Entity User To Update
        user.setFullName(profileRequest.getFullName());
        user.setBirthday(profileRequest.getBirthday());
        user.setAddress(profileRequest.getAddress());
        user.setPhone(profileRequest.getPhone());
        user.setEmail(profileRequest.getEmail());

        // Check Image Avatar
        MultipartFile multipartFile = profileRequest.getMultipartFile();

        if (multipartFile != null) {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Invalid image"));
            }

            // Folder To Save Avatar
            options.put("folder", avatar);

            if (user.getNameImage() != null) {
                // Delete Old Avatar
                cloudinaryService.delete(user.getNameImage(), options);
            }

            // Update New Avatar
            Map result = cloudinaryService.upload(multipartFile, options);
            user.setLinkImage(result.get("url").toString());
            user.setNameImage(result.get("public_id").toString());
        }

        // Update row of table User in Database
//        userRepository.save(user);

        return ResponseEntity.ok(userRepository.save(user));
    }
}
