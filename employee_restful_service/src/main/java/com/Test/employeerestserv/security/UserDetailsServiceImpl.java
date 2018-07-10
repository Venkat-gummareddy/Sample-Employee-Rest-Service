package com.Test.employeerestserv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Test.employeerestservice.user.User;
import com.Test.employeerestservice.user.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsImpl loadUserById(long id) throws UserIdNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserIdNotFoundException(String.valueOf(id));
        }

        return new UserDetailsImpl(user.get());
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user.get());
    }
}
