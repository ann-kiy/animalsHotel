package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/model_data")
public class ModelDataController {
    final TypeAnimalRepo typeAnimalRepo;
    final BreedAnimalRepo breedAnimalRepo;

    @Autowired
    public ModelDataController(TypeAnimalRepo typeAnimalRepo, BreedAnimalRepo breedAnimalRepo) {
        this.typeAnimalRepo = typeAnimalRepo;
        this.breedAnimalRepo = breedAnimalRepo;
    }

    @ModelAttribute(value = "typeAnimal")
    public List<TypeAnimal> allType(){
        return typeAnimalRepo.findAll();
    }

    @ModelAttribute(value = "breedAnimal")
    public List<BreedAnimal> allBreedType(@RequestParam("type") TypeAnimal typeAnimal){
        return breedAnimalRepo.findAllByType(typeAnimal);
    }
}
