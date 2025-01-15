package ru.vyatsu.medical_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vyatsu.medical_backend.store.entities.Role;
import ru.vyatsu.medical_backend.store.entities.User;
import ru.vyatsu.medical_backend.store.repositories.RoleRepository;
import ru.vyatsu.medical_backend.store.repositories.UserRepository;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userRepository.existsById(user.getEmail())) {
            result.rejectValue("email", "error.user", "User with this email already exists");
            return "register";
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        Role role = roleRepository.findByName("USER").orElseThrow();  // Например, роль "USER"
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
