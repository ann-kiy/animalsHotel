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



//    @Autowired
//    private PasswordEncoder passwordEncoder;
@GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Map<String,Object> model, User user, @RequestParam("file")MultipartFile file, @RequestParam("password2") String password2) throws IOException {
        if(!user.getPassword().equals(password2)){
            model.put("message","Пароли не созпадают!");
            return "registration";
        }
        user=userService.addImg(user,file);
        if(!userService.saveUser(user)){
            model.put("message","User exists!");
            return "registration";
        }
        return "/login"; 
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("message", "User successfully activated");
        }else{
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
