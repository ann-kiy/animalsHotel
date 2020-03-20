package com.kiyanitsa.animalsHotel.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Type_animal type_animal;
    @ManyToOne(fetch = FetchType.EAGER)
    private Breed_animal breed_animal;
    private byte age;
    private String img;
    private String info;

}
