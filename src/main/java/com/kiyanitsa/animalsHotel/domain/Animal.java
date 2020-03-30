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
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    private String name;
    private Sex sex;
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAnimal typeAnimal;
    @ManyToOne(fetch = FetchType.EAGER)
    private BreedAnimal breedAnimal;
    private byte age;
    private String img;
    private String info;

}
