package com.turkcell.authserver.business.concretes;

import com.turkcell.authserver.business.abstracts.UserService;
import com.turkcell.authserver.dataAccess.UserRepository;
import com.turkcell.authserver.entities.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException(""));
    }

    @Override
    public Integer getUserIdByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow().getId();
    }
}
