package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.*;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.services.AnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;
    private final TypeAnimalRepo typeAnimalRepo;
    private final BreedAnimalRepo breedAnimalRepo;

    public AnimalController(AnimalService animalService, TypeAnimalRepo typeAnimalRepo, BreedAnimalRepo breedAnimalRepo) {
        this.animalService = animalService;
        this.typeAnimalRepo = typeAnimalRepo;
        this.breedAnimalRepo = breedAnimalRepo;
    }
    @Value("${spring.profiles.active}")
    private String profile;

    @ModelAttribute(value = "isDevMode")
    public boolean isDevMode() {
        return "dev".equals(profile);
    }


    @PostMapping(consumes = "multipart/form-data")
    public Animal create( String type, String breed, Sex sexx, Animal animal, @RequestParam MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        if(animalService.chekAnimal(animal)) {
            animal.setOwner(user);
            animal.setSex(sexx);
            animal.setTypeAnimal(typeAnimalRepo.findByType(type));
            animal.setBreedAnimal(breedAnimalRepo.findByName(breed));
            animalService.addImg(animal,file);
            return animalService.getAnimalRepo().save(animal);
        }else{
            return null;
        }
    }
    @GetMapping("filter")
    @ResponseBody
    public List<Animal> filter(@RequestParam(value = "search") String search){
        return animalService.filter(search);
    }
    @GetMapping("{id}")
    public Animal getOne(@PathVariable("id") Animal animal){
        return animal;
    }

    @GetMapping("usr{id}")
    public List<Animal> getAnimals(@PathVariable("id")User user){
        return animalService.getAnimalRepo().findAllByOwnerAndState(user,true);
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
                String type, String breed, Sex sexx, Animal animal) throws IOException {
            animal.setOwner(animalFromBD.getOwner());
            animal.setImg(animalFromBD.getImg());
            animal.setSex(sexx);
            animal.setTypeAnimal(typeAnimalRepo.findByType(type));
            animal.setBreedAnimal(breedAnimalRepo.findByName(breed));
//        if(file!=null)
//            animalService.addImg(animal,file);
            BeanUtils.copyProperties(animal,animalFromBD,"id");
        return animalService.getAnimalRepo().save(animalFromBD);
    }
    @PostMapping("{id}")
    public Animal setAnimal(@PathVariable("id") Animal animal,
                         @RequestParam MultipartFile file) throws IOException {
        if(file!=null)
            animalService.addImg(animal,file);
        return animalService.getAnimalRepo().save(animal);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Animal animal){
        animal.setState(false);
        animalService.getAnimalRepo().save(animal);
    }
}
