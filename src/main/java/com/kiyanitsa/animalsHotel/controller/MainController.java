package com.kiyanitsa.animalsHotel.controller;


import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.*;
import com.kiyanitsa.animalsHotel.services.UserService;
import com.kiyanitsa.animalsHotel.test.JPASpecificationsTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;

    private final MessageRepo messageRepo;
    private final AdvertisementAcceptRepo advertisementAcceptRepo;
    private final AnimalRepo animalRepo;
    private  final BreedAnimalRepo breedAnimalRepo;
    private final TypeAnimalRepo typeAnimalRepo;


    @Autowired
    public MainController(UserService userService, MessageRepo messageRepo, AdvertisementAcceptRepo advertisementAcceptRepo, AnimalRepo animalRepo, BreedAnimalRepo breedAnimalRepo, TypeAnimalRepo typeAnimalRepo) {
        this.userService = userService;
        this.messageRepo = messageRepo;
        this.advertisementAcceptRepo = advertisementAcceptRepo;
        this.animalRepo = animalRepo;
        this.breedAnimalRepo = breedAnimalRepo;
        this.typeAnimalRepo = typeAnimalRepo;
    }

    @GetMapping
    public String main(@AuthenticationPrincipal User principal, Model model) {
//        JPASpecificationsTest a= new JPASpecificationsTest();
//        a.givenLast_whenGettingListOfUsers_thenCorrect(advertisementAcceptRepo,typeAnimalRepo,breedAnimalRepo);
        HashMap<Object, Object> data = new HashMap<>();
        if(principal==null ){
            data.put("profile", null);
            return "login";
        }
        data.put("profile", principal);
        data.put("messages", messageRepo.findAllByAuthor(principal));
        model.addAttribute("frontendData", data);
            return "index";
    }
//    @GetMapping("{id}")
//    public String showUser(@PathVariable("id") User principal, Model model) {
//        HashMap<Object, Object> data = new HashMap<>();
//        if(principal==null ){
//            data.put("profile", null);
//            return "login";
//        }
//        data.put("profile", principal);
//        data.put("messages", messageRepo.findAllByAuthor(principal));
//        model.addAttribute("frontendData", data);
//        return "index";
//    }
    @GetMapping("profile")
    public String profile(Model model, @AuthenticationPrincipal User user){
        if(user==null){
            return "redirect:/login";
        }
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone",user.getPhone());
        model.addAttribute("locale",user.getLocale());
        return "profile";
    }

    @PostMapping("profile")
    public String changeProfile(Map<String,Object> model, User user,@AuthenticationPrincipal User user1, @RequestParam("file") MultipartFile file) throws IOException {
        if(!userService.isFullDataUser(user)){
            model.put("message","Не все обязательные поля заполненны!");
            model.put("name", user.getName());
            model.put("email", user.getEmail());
            model.put("phone",user.getPhone());
            model.put("locale",user.getLocale());
            return "profile";
        }
        user1=userService.addImg(user1,file);
        userService.updateUser(user,user1);
        return "redirect:/login";
    }
}
