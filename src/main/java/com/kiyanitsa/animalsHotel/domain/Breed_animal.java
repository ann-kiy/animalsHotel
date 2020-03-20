package com.kiyanitsa.animalsHotel.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Breed_animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Type_animal type;
    private String name;
}
