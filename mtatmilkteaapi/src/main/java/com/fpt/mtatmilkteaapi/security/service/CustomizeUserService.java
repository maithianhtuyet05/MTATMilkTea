package com.fpt.mtatmilkteaapi.security.service;

import com.fpt.mtatmilkteaapi.entity.User;
import com.fpt.mtatmilkteaapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomizeUserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndDeletedAtNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With Username: " + username));
        return CustomizeUser.build(user);
    }
}
