package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.Response;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.ResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResponseService {
    @Autowired
    ResponseRepo responseRepo;

    public void respond(Response response){
        Response testR=responseRepo.findByUserAndAdvertisement(response.getUser(),response.getAdvertisement());
        if(testR==null){
            response.setCreateDate(LocalDateTime.now());
            response.setState(true);
            response.setStateRecipient(false);
            responseRepo.save(response);
        }
    }
    public void confirm(Response response){
        Response testR=responseRepo.findByUserAndAdvertisement(response.getUser(),response.getAdvertisement());
        if(testR!=null){
            response.setStateRecipient(true);
            responseRepo.save(response);
        }
    }
    public void delete(Response response){
        Response testR=responseRepo.findByUserAndAdvertisement(response.getUser(),response.getAdvertisement());
        if(testR!=null){
            response.setState(false);
            responseRepo.save(response);
        }
    }
    public List<Response> getByUser(User user){
        if(user!=null){
            return  responseRepo.findByUserAndState(user, true);
        }
        return null;
    }
    public List<Response> getByRecipient(User user){
        if(user!=null){
            return  responseRepo.findByAdvertisement_UserAndState(user, true);
        }
        return null;
    }
    public List<Response> getByAdvertisement(AdvertisementAccept advertisement){
        if(advertisement!=null){
            return  responseRepo.findByAdvertisementAndState(advertisement,true);
        }
        return null;
    }
    public Response getByAdvertisementAndUser(AdvertisementAccept advertisement, User user){
        if(advertisement!=null){
            return  responseRepo.findByUserAndAdvertisementAndState(user,advertisement, true);
        }
        return null;
    }

}
