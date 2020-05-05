package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.Sex;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.services.AdvertisementAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementAcceptController {
    @Autowired
   private AdvertisementAcceptService service;


    @GetMapping
    @ResponseBody
    public List<AdvertisementAccept> search(@RequestParam(value = "search") String search) {
        return service.filter(search);
    }

    @GetMapping("{/id}")
    public AdvertisementAccept getAdvertId(@PathVariable("id") AdvertisementAccept advertisementAccept){
        return service.getAdvertId(advertisementAccept);
    }

    @GetMapping("usr{id}")
    public List<AdvertisementAccept> getAdvertUserId(@PathVariable("id") User user){

        return service.getAdvertUserId(user);
    }
    @PostMapping
    public AdvertisementAccept create( @RequestBody AdvertisementAccept advertisementAccept, @AuthenticationPrincipal User user){
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
