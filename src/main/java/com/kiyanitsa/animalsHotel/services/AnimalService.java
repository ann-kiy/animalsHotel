package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Animal;
import com.kiyanitsa.animalsHotel.domain.Role;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.AnimalRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
@Data
public class AnimalService {
    AnimalRepo animalRepo;
    @Value("${upload.path}")
    private String uploadPath;

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
