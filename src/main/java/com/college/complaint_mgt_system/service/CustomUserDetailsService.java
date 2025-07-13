package com.college.complaint_mgt_system.service;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.college.complaint_mgt_system.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.college.complaint_mgt_system.model.User user =

            userRepository.findByEmail(email)

                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User

                .withUsername(user.getEmail())

                .password(user.getPassword()) // plain text for now

                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))

                .build();

    }

}