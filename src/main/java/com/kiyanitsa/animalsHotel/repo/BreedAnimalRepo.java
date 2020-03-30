package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedAnimalRepo extends JpaRepository<BreedAnimal,Long> {
}
