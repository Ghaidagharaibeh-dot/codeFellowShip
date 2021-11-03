package com.example.codefellowship.Security;

import com.example.codefellowship.Repo.PostRepo;
import com.example.codefellowship.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        UserRepo userRepo;

        @Autowired
        PostRepo postRepo;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepo.findByUsername(username);
        }
    }

