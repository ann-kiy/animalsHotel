package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.model.UserAndFile;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import com.kiyanitsa.animalsHotel.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Value("${spring.profiles.active}")
    private String profile;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
@ModelAttribute(value = "isDevMode")
public boolean isDevMode() {
    return "dev".equals(profile);
}

    @GetMapping("/registration")
    public String registration()
    {
        return "header";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestPart("file") MultipartFile file) throws IOException {
        user=userService.addImg(user,file);
        userService.saveUser(user);
        return "header";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
//        User user=userService.findByCode(code);
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("isActivated", true);
        }else{
            model.addAttribute("isActivated", false);
        }
        return "header";
    }
}
