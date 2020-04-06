package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedAnimalRepo extends JpaRepository<BreedAnimal,Long> {
    List<BreedAnimal> findAllByType(TypeAnimal typeAnimal);
}
