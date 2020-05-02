package com.kiyanitsa.animalsHotel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class AdvertisementAccept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAnimal typeAnimal;

    @ManyToOne(fetch = FetchType.EAGER)
    private BreedAnimal breedAnimal;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateStart;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateEnd;

    private String condition;
    private boolean state;
    private String info;
    private byte age;
    private Sex sex;

}
