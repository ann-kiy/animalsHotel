package com.kiyanitsa.animalsHotel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDateTime dateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDateTime dateEnd;

    private String condition;
    private boolean state;
    private String info;
    private byte age;
    private Sex sex;

}
