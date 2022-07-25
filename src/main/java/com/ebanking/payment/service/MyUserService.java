package com.ebanking.payment.service;

import com.ebanking.payment.model.MyUser;
import com.ebanking.payment.repository.MyUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class MyUserService implements UserDetailsService {

    private final MyUserRepo myUserRepo;

    private final PasswordEncoder passwordEncoder;

    public MyUser saveUser(MyUser user) {
        log.info("Saving new user {} to the db", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepo.save(user);
    }

    public List<MyUser> getUsers() {
        log.info("Fetching all users");
        return myUserRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser user = myUserRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the db");
            throw new UsernameNotFoundException("User not found in the db");
        } else {
            log.info("User found in the db: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
