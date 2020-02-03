package com.kiyanitsa.animalsHotel.controller;


import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.MessageRepo;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserDetailsRepo userDetailsRepo;

    private final MessageRepo messageRepo;

    @Autowired
    public MainController( UserDetailsRepo userDetailsRepo, MessageRepo messageRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public String main(@AuthenticationPrincipal Principal principal, Model model) {
       HashMap<Object, Object> data = new HashMap<>();
        if(principal==null)
            data.put("profile", null);
        else {
           if(userDetailsRepo.findByIdWeb(principal.getName()).isEmpty())
               data.put("profile", principal);
           else
                data.put("profile", userDetailsRepo.findByIdWeb(principal.getName()).get());
        }

        data.put("messages", messageRepo.findAll());
        model.addAttribute("frontendData", data);
        return "index";
    }
}
