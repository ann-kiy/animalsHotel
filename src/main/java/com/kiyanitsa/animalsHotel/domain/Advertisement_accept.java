package com.kiyanitsa.animalsHotel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Advertisement_accept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Type_animal type_animal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Breed_animal breed_animal;

    private String condition;
    private boolean state;
    private String info;

}
