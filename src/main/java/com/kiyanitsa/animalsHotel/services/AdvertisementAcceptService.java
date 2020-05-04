package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.AdvertisementAcceptRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecificationsBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdvertisementAcceptService {
    final BreedAnimalRepo breedAnimalRepo;
    final TypeAnimalRepo typeAnimalRepo;
    final AdvertisementAcceptRepo repo;
    final UserDetailsRepo userDetailsRepo;

    @Autowired
    public AdvertisementAcceptService(BreedAnimalRepo breedAnimalRepo, TypeAnimalRepo typeAnimalRepo, AdvertisementAcceptRepo repo, UserDetailsRepo userDetailsRepo) {
        this.breedAnimalRepo = breedAnimalRepo;
        this.typeAnimalRepo = typeAnimalRepo;
        this.repo = repo;
        this.userDetailsRepo = userDetailsRepo;
    }

    public List<AdvertisementAccept> filter(String filter){
        AdvertAcceptSpecificationsBuilder builder = new AdvertAcceptSpecificationsBuilder();
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

        Specification<AdvertisementAccept> spec = builder.build();

        return repo.findAll(spec);
    }
    public AdvertisementAccept save(AdvertisementAccept advertisementAccept, User user){
        advertisementAccept.setUser(user);
        advertisementAccept.setState(true);
        advertisementAccept.setCreateDate(LocalDateTime.now());
        return repo.save(advertisementAccept);
    }
    public AdvertisementAccept update(AdvertisementAccept advert1, AdvertisementAccept advert2){
        BeanUtils.copyProperties(advert1,advert2,"id");
        advert1.setCreateDate(LocalDateTime.now());
        return repo.save(advert1);
    }
    public boolean delete(AdvertisementAccept advertisementAccept){
        if(repo.existsById(advertisementAccept.getId())){
            advertisementAccept.setState(false);
            repo.save(advertisementAccept);
            return true;
        }
        return false;
    }
    public List<AdvertisementAccept> getAdvertId(AdvertisementAccept advertisementAccept){
        return repo.findByIdAndState(advertisementAccept,true);
    }
    public List<AdvertisementAccept> getAdvertUserId(User user){
        return repo.findAllByUserAndState(user,true);
    }
}
