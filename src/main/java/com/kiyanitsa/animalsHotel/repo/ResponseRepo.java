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
    List<Response> findByAdvertisementAndState(AdvertisementAccept advertisement, boolean state);
    List<Response> findByAdvertisement_UserAndState(User user, boolean state);

}
