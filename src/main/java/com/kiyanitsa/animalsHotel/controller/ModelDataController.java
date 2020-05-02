package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelDataController {
    final TypeAnimalRepo typeAnimalRepo;
    final BreedAnimalRepo breedAnimalRepo;

    @Autowired
    public ModelDataController(TypeAnimalRepo typeAnimalRepo, BreedAnimalRepo breedAnimalRepo) {
        this.typeAnimalRepo = typeAnimalRepo;
        this.breedAnimalRepo = breedAnimalRepo;
    }

    @GetMapping
    public List<TypeAnimal> allType(){
        return typeAnimalRepo.findAll();
    }

    @GetMapping("{type}")
    public List<BreedAnimal> allBreedType(@PathVariable("type") String typeAnimal){
        return breedAnimalRepo.findAllByType(typeAnimalRepo.findByType(typeAnimal));
    }
}
