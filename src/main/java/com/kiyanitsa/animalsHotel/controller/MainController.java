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
import java.util.Optional;

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
    public String main(@AuthenticationPrincipal User principal, @AuthenticationPrincipal Principal principal1, Model model) {
       HashMap<Object, Object> data = new HashMap<>();
        if(principal==null && principal1==null)
            data.put("profile", null);
        else if(principal==null) {
                data.put("profile", userDetailsRepo.findByIdWeb(principal1.getName()).get());
        }else {
                data.put("profile", principal);
        }

        data.put("messages", messageRepo.findAll());
        model.addAttribute("frontendData", data);
        return "index";
    }

}
