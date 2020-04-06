package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.AdvertisementGive;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.AdvertisementGiveRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.services.AdvertisementAcceptService;
import com.kiyanitsa.animalsHotel.services.AdvertisementGiveService;
import com.kiyanitsa.animalsHotel.services.AnimalService;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecificationsBuilder;
import com.kiyanitsa.animalsHotel.specification.AdvertGiveSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/advertisement_give")
public class AdvertisementGiveController {
    @Autowired
    private AdvertisementGiveService service;

    @GetMapping
    @ResponseBody
    public List<AdvertisementGive> search(@RequestParam(value = "search") String search) {
        return service.filter(search);
    }

    @GetMapping("{/id}")
    public List<AdvertisementGive> getAdvertId(@RequestParam("id") User user){
        return service.getAdvertId(user);
    }

    @PostMapping
    @ResponseBody
    public AdvertisementGive create(@RequestBody AdvertisementGive advertisementGive, @AuthenticationPrincipal User user){
        return service.save(advertisementGive, user);
    }

    @PutMapping("{id}")
    @ResponseBody
    public AdvertisementGive update(@RequestBody AdvertisementGive advertisementGive, @RequestParam("id") AdvertisementGive advertFromDB ){
        return service.update(advertisementGive,advertFromDB);
    }

    @DeleteMapping
    public boolean delete(AdvertisementGive advertisementGive){
        return service.delete(advertisementGive);
    }
}
