package com.kiyanitsa.animalsHotel.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.Views;
import com.kiyanitsa.animalsHotel.exceptions.NotFoundException;
import com.kiyanitsa.animalsHotel.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    private int counter=4;
    private final MessageRepo messageRepo;
//    private List<Map<String,String>> messages=new ArrayList<>(){{
//        add(new HashMap<String,String>(){{put("id","1"); put("text","First message");}});
//        add(new HashMap<String,String>(){{put("id","2"); put("text","Second message");}});
//        add(new HashMap<String,String>(){{put("id","3"); put("text","Third message");}});
//    }};
    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreateDate(LocalDateTime.now());
        return messageRepo.save(message);
    }
    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromBD,
                                     @RequestBody Message message) {
        BeanUtils.copyProperties(message,messageFromBD,"id");
        return messageRepo.save(messageFromBD);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);

    }
}
