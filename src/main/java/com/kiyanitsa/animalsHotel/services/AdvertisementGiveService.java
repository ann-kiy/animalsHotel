package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.AdvertisementGive;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.*;
import com.kiyanitsa.animalsHotel.specification.AdvertGiveSpecificationBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdvertisementGiveService {
    final BreedAnimalRepo breedAnimalRepo;
    final TypeAnimalRepo typeAnimalRepo;
    final AdvertisementGiveRepo repo;
    final UserDetailsRepo userDetailsRepo;

    @Autowired
    public AdvertisementGiveService(BreedAnimalRepo breedAnimalRepo, TypeAnimalRepo typeAnimalRepo, AdvertisementGiveRepo repo, UserDetailsRepo userDetailsRepo) {
        this.breedAnimalRepo = breedAnimalRepo;
        this.typeAnimalRepo = typeAnimalRepo;
        this.repo = repo;
        this.userDetailsRepo = userDetailsRepo;
    }

    public List<AdvertisementGive> filter(String filter){
        AdvertGiveSpecificationBuilder builder = new AdvertGiveSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
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
    }
    public AdvertisementGive save(AdvertisementGive advertisementGive, User user){
        advertisementGive.setUser(user);
       // advertisementGive.setAnimal(animal);
        advertisementGive.setState(true);
        advertisementGive.setCreateDate(LocalDateTime.now());
        return repo.save(advertisementGive);
    }
    public AdvertisementGive update(AdvertisementGive advert1, AdvertisementGive advert2){
        BeanUtils.copyProperties(advert1,advert2,"id");
        advert1.setCreateDate(LocalDateTime.now());
        return repo.save(advert1);
    }
    public boolean delete(AdvertisementGive advertisementGive){
        if(repo.existsById(advertisementGive.getId())){
            advertisementGive.setState(false);
            repo.save(advertisementGive);
            return true;
        }
        return false;
    }
    public List<AdvertisementGive> getAdvertId(User user){
        return repo.findAllByUserAndState(user,true);
    }
}
