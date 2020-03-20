package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.Breed_animal;
import com.kiyanitsa.animalsHotel.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Breed_animalRepo extends JpaRepository<Breed_animal,Long> {
}
