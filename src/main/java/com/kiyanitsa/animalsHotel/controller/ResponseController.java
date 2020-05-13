package com.kiyanitsa.animalsHotel.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.Response;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.services.ResponseService;
import com.kiyanitsa.animalsHotel.views.ViewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/response")
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @GetMapping("/recipient")
    public List<Response> getResponseByRecipient(@AuthenticationPrincipal User user){
        return responseService.getByRecipient(user);
    }
    @GetMapping("advert{id}")
    public List<Response> getResponse(@PathVariable("id") AdvertisementAccept advertisementAccept){
        return responseService.getRespByAdvert(advertisementAccept);
    }
    @GetMapping("/user")
    public List<Response> getResponseByUser(@AuthenticationPrincipal User user){
        return responseService.getByUser(user);
    }
    @PostMapping
    public void add(@RequestBody Response response){
        responseService.respond(response);
    }
    @GetMapping("{id}")
    public boolean isResponse(@PathVariable("id") AdvertisementAccept advertisementAccept,@AuthenticationPrincipal User user ){
        return responseService.isRes(advertisementAccept,user);
    }

    @PostMapping("active/{id}")
    public void confirm(@PathVariable("id") Response response){
        responseService.confirm(response);
    }

    @PostMapping("cancel/{id}")
    public void cancel(@PathVariable("id") Response response){
        responseService.cancel(response);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") AdvertisementAccept advertisementAccept,@AuthenticationPrincipal User user){
        responseService.delete(advertisementAccept,user);
    }

    @DeleteMapping("/by{id}")
    public void deleteByResp(@PathVariable("id") Response response){
        responseService.deleteByResp(response);
    }
}
