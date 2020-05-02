package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAnimalRepo extends JpaRepository<TypeAnimal,Long> {
    TypeAnimal findByType(String type);

}
