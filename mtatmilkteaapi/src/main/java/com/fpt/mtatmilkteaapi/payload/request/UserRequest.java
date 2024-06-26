package com.fpt.mtatmilkteaapi.payload.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private long id;
    private String username;
    private String fullName;
    private Date birthday; // 1995-10-29
    private int gender; // 0: Nam, 1: Nữ, 3: Khác
    private String address;
    private String postcode;
    private String phone;
    private MultipartFile avatar;
    private String email;
    private Set<String> roles;
    private String status;

}
