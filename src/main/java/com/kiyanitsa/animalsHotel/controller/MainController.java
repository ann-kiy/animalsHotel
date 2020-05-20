package com.kiyanitsa.animalsHotel.controller;


import com.kiyanitsa.animalsHotel.domain.Animal;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.*;
import com.kiyanitsa.animalsHotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.profiles.active}")
    private String profile;
    @Autowired
    public MainController(UserService userService, MessageRepo messageRepo, AdvertisementAcceptRepo advertisementAcceptRepo, AnimalRepo animalRepo, BreedAnimalRepo breedAnimalRepo, TypeAnimalRepo typeAnimalRepo) {
        this.userService = userService;
        this.messageRepo = messageRepo;
        this.advertisementAcceptRepo = advertisementAcceptRepo;
        this.animalRepo = animalRepo;
        this.breedAnimalRepo = breedAnimalRepo;
        this.typeAnimalRepo = typeAnimalRepo;
    }

    @ModelAttribute(value = "authUser")
    public User getAuth(@AuthenticationPrincipal User principal) {
        if(principal==null)
            return null;
        return userService.findById(principal.getId());
    }

    @GetMapping
    public String main(@AuthenticationPrincipal User principal, Model model) {
//        JPASpecificationsTest a= new JPASpecificationsTest();
//        a.givenLast_whenGettingListOfUsers_thenCorrect(advertisementAcceptRepo,typeAnimalRepo,breedAnimalRepo);
        HashMap<Object, Object> data = new HashMap<>();
        if(principal==null ){
            model.addAttribute("isDevMode","dev".equals(profile));
            return "redirect:/login";
        }
        User user=userService.findById(principal.getId());
        data.put("profile", user);
        data.put("messages", messageRepo.findAllByRecipient(user));
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode","dev".equals(profile));
            return "header";
    }
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("frontendData", null);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "header";
    }
    @GetMapping("user/{id}")
    public String showUser(@PathVariable("id") User user, Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        if(user==null ){
            data.put("profile", null);
            //return "login";
        }
        data.put("profile", user);
        data.put("messages", messageRepo.findAllByRecipient(user));
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode","dev".equals(profile));
        return "header";
    }
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

    @PutMapping("profile")
    public User changeProfile( @RequestBody User user, MultipartFile file, @AuthenticationPrincipal User userFromBD) throws IOException {
        if(file!=null)
            userFromBD=userService.addImg(userFromBD,file);
        return userService.updateUser(user,userFromBD);
//        return null;
    }

    @PostMapping("profile")
    public void setAnimal(@AuthenticationPrincipal User user,
                            @RequestParam MultipartFile file) throws IOException {
        user=userService.findById(user.getId());
        if(file!=null)
            user=userService.addImg(user,file);
        userService.saveUser(user);
    }
}
