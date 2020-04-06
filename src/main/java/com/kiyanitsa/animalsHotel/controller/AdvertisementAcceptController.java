package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.AdvertisementAcceptRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.services.AdvertisementAcceptService;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/advertisement_accept")
public class AdvertisementAcceptController {
    @Autowired
   private AdvertisementAcceptService service;

    @GetMapping
    @ResponseBody
    public List<AdvertisementAccept> search(@RequestParam(value = "search") String search) {
        return service.filter(search);
    }

    @GetMapping("{/id}")
    public List<AdvertisementAccept> getAdvertId(@RequestParam("id") User user){
        return service.getAdvertId(user);
    }

    @PostMapping
    @ResponseBody
    public AdvertisementAccept create(@RequestBody AdvertisementAccept advertisementAccept, @AuthenticationPrincipal User user){
        return service.save(advertisementAccept, user);
    }

    @PutMapping("{id}")
    @ResponseBody
    public AdvertisementAccept update(@RequestBody AdvertisementAccept advertisementAccept, @RequestParam("id") AdvertisementAccept advertFromDB ){
        return service.update(advertisementAccept,advertFromDB);
    }

    @DeleteMapping
    public boolean delete(AdvertisementAccept advertisementAccept){
        return service.delete(advertisementAccept);
    }

}
