package com.kiyanitsa.animalsHotel.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class BreedAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAnimal type;
    private String name;
}
