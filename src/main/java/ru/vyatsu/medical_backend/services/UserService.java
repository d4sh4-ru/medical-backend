package ru.vyatsu.medical_backend.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.store.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ru.vyatsu.medical_backend.store.entities.User appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPasswordHash())
                .roles(appUser.getRole().getName())
                .build();
    }

    public ru.vyatsu.medical_backend.store.entities.User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
