package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.AdvertisementGive;
import com.kiyanitsa.animalsHotel.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdvertisementGiveRepo extends JpaRepository<AdvertisementGive, Long>, JpaSpecificationExecutor<AdvertisementGive> {
    List<AdvertisementGive> findAllByAnimal(Object animals);
}
