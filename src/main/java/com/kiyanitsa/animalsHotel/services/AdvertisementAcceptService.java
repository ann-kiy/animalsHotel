package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.Sex;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(.+?)(,|;)");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            if(matcher.group(1).equals("breedAnimal"))
                builder.with(matcher.group(1), matcher.group(2), Long.parseLong(matcher.group(3))!=0?breedAnimalRepo.findById(Long.parseLong(matcher.group(3))).get():null,matcher.group(4));
            else if(matcher.group(1).equals("typeAnimal"))
                builder.with(matcher.group(1), matcher.group(2), Long.parseLong(matcher.group(3))!=0?typeAnimalRepo.findById(Long.parseLong(matcher.group(3))).get():null,matcher.group(4));
            else if(matcher.group(1).equals("sex")){
                if(matcher.group(3).equals("м"))
                    builder.with(matcher.group(1), matcher.group(2), Sex.м,matcher.group(4));
                else if(matcher.group(3).equals("ж"))
                    builder.with(matcher.group(1), matcher.group(2), Sex.ж,matcher.group(4));
                else
                    builder.with(matcher.group(1), matcher.group(2), null,matcher.group(4));
            }else if(matcher.group(1).equals("state")){
                builder.with(matcher.group(1), matcher.group(2), Boolean.valueOf(matcher.group(3)),matcher.group(4));
            }else if(matcher.group(1).equals("dateStart") || matcher.group(1).equals("dateEnd") || matcher.group(1).equals("createDate")){
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                Date date=Date.from(LocalDateTime.parse(matcher.group(3), formatter).atZone(ZoneId.systemDefault()).toInstant());
                builder.with(matcher.group(1), matcher.group(2), LocalDate.parse(matcher.group(3)),matcher.group(4));
            }else {
                if(matcher.group(3).equals("-1"))
                    builder.with(matcher.group(1), matcher.group(2), null, matcher.group(4));
                else
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
            }
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
        advert1.setUser(advert2.getUser());
        BeanUtils.copyProperties(advert1,advert2,"id");

        advert2.setState(true);
        advert2.setCreateDate(LocalDateTime.now());
        return repo.save(advert2);
    }
    public boolean delete(AdvertisementAccept advertisementAccept){
        if(repo.existsById(advertisementAccept.getId())){
            advertisementAccept.setState(false);
            repo.save(advertisementAccept);
            return true;
        }
        return false;
    }
    public AdvertisementAccept getAdvertId(AdvertisementAccept advertisementAccept){
        return advertisementAccept;
    }
    public List<AdvertisementAccept> getAdvertUserId(User user){
        return repo.findAllByUserAndState(user,true);
    }
}
