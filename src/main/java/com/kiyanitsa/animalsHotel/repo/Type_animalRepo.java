package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.Type_animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Type_animalRepo extends JpaRepository<Type_animal,Long> {
}
