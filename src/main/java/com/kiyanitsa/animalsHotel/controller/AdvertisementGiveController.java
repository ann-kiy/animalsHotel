package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.AdvertisementGive;
import com.kiyanitsa.animalsHotel.repo.AdvertisementGiveRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.services.AnimalService;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecificationsBuilder;
import com.kiyanitsa.animalsHotel.specification.AdvertGiveSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdvertisementGiveController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private AdvertisementGiveRepo repo;
    @Autowired
    private BreedAnimalRepo breedAnimalRepo;
    @Autowired
    private TypeAnimalRepo typeAnimalRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/advertisement_give")
    @ResponseBody
    public List<AdvertisementGive> search(@RequestParam(value = "search") String search) {
        AdvertGiveSpecificationBuilder builder = new AdvertGiveSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            if(matcher.group(1).equals("breedAnimal"))
                builder.with(matcher.group(1), matcher.group(2), breedAnimalRepo.findById(Long.parseLong(matcher.group(3))).get());
            else if(matcher.group(1).equals("typeAnimal"))
                builder.with(matcher.group(1), matcher.group(2), typeAnimalRepo.findById(Long.parseLong(matcher.group(3))).get());
            else
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<AdvertisementGive> spec = builder.build();
        return repo.findAll(spec);
//        return repo.findAllByAnimal(animalService.filter(search));
    }
}
