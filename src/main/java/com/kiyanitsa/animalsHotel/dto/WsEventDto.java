package com.kiyanitsa.animalsHotel.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.views.ViewMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(ViewMessage.IdNameAuthorRecipient.class)
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    //@JsonRawValue
    private Message body;
//    @JsonRawValue
//    private String author;
//    @JsonRawValue
//    private String recipient;
}
