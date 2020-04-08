package com.kiyanitsa.animalsHotel.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.domain.Views;
import com.kiyanitsa.animalsHotel.exceptions.NotFoundException;
import com.kiyanitsa.animalsHotel.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageRepo messageRepo;
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
    public Message create(@RequestBody Message message, @AuthenticationPrincipal User user){
        message.setCreateDate(LocalDateTime.now());
        message.setAuthor(user);
        return messageRepo.save(message);
    }
    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromBD,
                                     @RequestBody Message message) {
        messageFromBD.setText(message.getText());
        messageFromBD.setCreateDate(LocalDateTime.now());
        return messageRepo.save(messageFromBD);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);

    }
}
