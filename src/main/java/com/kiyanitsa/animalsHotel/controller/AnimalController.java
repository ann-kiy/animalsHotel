package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.*;
import com.kiyanitsa.animalsHotel.services.AnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public Animal create(@RequestBody Animal animal, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        if(animalService.chekAnimal(animal)) {
            animal.setOwner(user);
            animalService.addImg(animal,file);
            return animalService.getAnimalRepo().save(animal);
        }else{
            return null;
        }
    }
    @GetMapping("{id}")
    public Animal getOne(@PathVariable("id") Animal animal){
        return animal;
    }
    @GetMapping("user/{id}")
    public List<Animal> getAnimals(User user){
        return animalService.getAnimalRepo().findAllByOwner(user);
    }
    @GetMapping("type/{id}")
    public List<Animal> getAnimalsType(TypeAnimal typeAnimal){
        return animalService.getAnimalRepo().findAllByTypeAnimal(typeAnimal);
    }
    @GetMapping("breed/{id}")
    public List<Animal> getAnimalsBreed(BreedAnimal breedAnimal){
        return animalService.getAnimalRepo().findAllByBreedAnimal(breedAnimal);
    }
    @GetMapping("age")
    public List<Animal> getAnimalsAge(byte age){
        return animalService.getAnimalRepo().findAllByAge(age);
    }
    @GetMapping("sex")
    public List<Animal> getAnimalsSex(Sex sex){
        return animalService.getAnimalRepo().findAllBySex(sex);
    }
    @PutMapping("{id}")
    public Animal update(@PathVariable("id") Animal animalFromBD,
                          @RequestBody Animal animal) {
        BeanUtils.copyProperties(animal,animalFromBD,"id");
        return animalService.getAnimalRepo().save(animalFromBD);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Animal animal){
        animalService.getAnimalRepo().delete(animal);
    }
}
