package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/registration")
@Controller
public class RegistrationController {
    private UserDetailsRepo userDetailsRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registration()
    {
        return "registration";
    }

    @PostMapping
    public String addUser(String name, String username, String password)
    {
//        User user = new User();
//        user.setName(name);
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//
//        userRepo.save(user);

        return "redirect:/login";
    }
}
