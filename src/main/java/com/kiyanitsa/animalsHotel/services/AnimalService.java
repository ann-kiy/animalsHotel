package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Animal;
import com.kiyanitsa.animalsHotel.domain.Role;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.AnimalRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
@Data
public class AnimalService {
    AnimalRepo animalRepo;

    public boolean chekAnimal(Animal animal){
   //     Animal animalFromDB=animalRepo.findById(animal.getId()).orElseGet(null);
        if(animal.getName().equals("")){
            return false;
        }
        return true;
    }
}
