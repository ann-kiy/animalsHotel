package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Animal;
import com.kiyanitsa.animalsHotel.repo.AnimalRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecificationsBuilder;
import com.kiyanitsa.animalsHotel.specification.AnimalSpecificationBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
public class AnimalService {
    @Autowired
    AnimalRepo animalRepo;
    @Autowired
    TypeAnimalRepo typeAnimalRepo;
    @Autowired
    BreedAnimalRepo breedAnimalRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public List<Animal> filter(String filter){
        AnimalSpecificationBuilder builder = new AnimalSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            if(matcher.group(1).equals("breedAnimal"))
                builder.with(matcher.group(1), matcher.group(2), breedAnimalRepo.findById(Long.parseLong(matcher.group(3))).get(), matcher.group(4));
            else if(matcher.group(1).equals("typeAnimal"))
                builder.with(matcher.group(1), matcher.group(2), typeAnimalRepo.findById(Long.parseLong(matcher.group(3))).get(), matcher.group(4));
            else
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        Specification<Animal> spec = builder.build();
        return animalRepo.findAll(spec);
    }

    public boolean chekAnimal(Animal animal){
   //     Animal animalFromDB=animalRepo.findById(animal.getId()).orElseGet(null);
        if(animal.getName().equals("")){
            return false;
        }
        return true;
    }
    public Animal addImg(Animal animal, MultipartFile file) throws IOException {
        if(!file.getOriginalFilename().equals("")) {
            File uploadDir= new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultFileName = UUID.randomUUID().toString()+"."+file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            animal.setImg(resultFileName);
        }
        return animal;
    }
}
