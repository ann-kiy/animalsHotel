package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import com.kiyanitsa.animalsHotel.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;



//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Map<String,Object> model, User user, @RequestParam("file")MultipartFile file) throws IOException {
        user=userService.addImg(user,file);
        if(!userService.saveUser(user)){
            model.put("message","User exists!");
            return "registration";
        }
        return "/login"; 
    }
}
