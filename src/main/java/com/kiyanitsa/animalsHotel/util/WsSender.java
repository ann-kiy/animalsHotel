package com.kiyanitsa.animalsHotel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.dto.EventType;
import com.kiyanitsa.animalsHotel.dto.ObjectType;
import com.kiyanitsa.animalsHotel.dto.WsEventDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.sockjs.frame.SockJsMessageCodec;

import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class WsSender {
    private  final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WsSender(SimpMessagingTemplate simpMessagingTemplate, ObjectMapper objectMapper){
        this.template=simpMessagingTemplate;
        this.mapper=objectMapper;
    }
    public <T> BiConsumer<EventType,T> getSender(ObjectType objectType, Class view){
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);
        return (EventType eventType, T payload)-> {
            String value=null;
            ObjectWriter s;
            try{
                value=writer.writeValueAsString(payload);
               // s=writer.withAttribute("name", payload);
            }catch (JsonProcessingException e){
                throw new RuntimeException(e);
            }
            template.convertAndSend(
                    "/topic/activity",
                    new WsEventDto(objectType, eventType, (Message) payload)
            );
        };
    }
}
