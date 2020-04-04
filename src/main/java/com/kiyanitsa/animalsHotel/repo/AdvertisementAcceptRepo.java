package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdvertisementAcceptRepo extends JpaRepository<AdvertisementAccept, Long>, JpaSpecificationExecutor<AdvertisementAccept> {
  //   List<AdvertisementAccept> findAll(AdvertAcceptSpecification advertAcceptSpecification);
}
