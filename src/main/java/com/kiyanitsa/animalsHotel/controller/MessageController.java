package com.kiyanitsa.animalsHotel.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.dto.EventType;
import com.kiyanitsa.animalsHotel.dto.ObjectType;
import com.kiyanitsa.animalsHotel.repo.MessageRepo;
import com.kiyanitsa.animalsHotel.util.WsSender;
import com.kiyanitsa.animalsHotel.views.ViewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;
    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE,ViewMessage.IdNameAuthorRecipient.class);
    }

    @GetMapping
    @JsonView(ViewMessage.IdNameAuthorRecipient.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(ViewMessage.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message, @AuthenticationPrincipal User user){
        message.setCreateDate(LocalDateTime.now());
        message.setAuthor(user);
        Message updatedMessage=messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updatedMessage);
        return updatedMessage;
    }
    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromBD,
                                     @RequestBody Message message) {
        messageFromBD.setText(message.getText());
        messageFromBD.setCreateDate(LocalDateTime.now());
        Message updatedMessage= messageRepo.save(messageFromBD);

        wsSender.accept(EventType.UPDATE, updatedMessage);
        return updatedMessage;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

//    @MessageMapping("/changeMessage")
//    @SendTo("/topic/activity")
//    public Message change(Message message){
//        return messageRepo.save(message);
//    }
}
