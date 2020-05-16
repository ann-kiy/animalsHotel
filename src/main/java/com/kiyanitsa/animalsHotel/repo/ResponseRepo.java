package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.Response;
import com.kiyanitsa.animalsHotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepo  extends JpaRepository<Response,Long> {
    Response findByUserAndAdvertisementAndState(User user, AdvertisementAccept advertisement, boolean state);
    Response findByUserAndAdvertisement(User user, AdvertisementAccept advertisement);
    List<Response> findByUserAndState(User user, boolean state);
    List<Response> findByAdvertisementAndStateAndAnimal_State(AdvertisementAccept advertisement, boolean state, boolean animalState);
    List<Response> findByAdvertisement_UserAndState(User user, boolean state);

}
