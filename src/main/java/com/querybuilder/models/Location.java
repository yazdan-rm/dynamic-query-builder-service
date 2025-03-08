package com.querybuilder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @OneToOne
    @JoinColumn(name = "fk_job")
    private Job job;

    // Getters and Setters
}
