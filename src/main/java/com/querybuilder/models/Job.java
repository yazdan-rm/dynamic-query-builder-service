package com.querybuilder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "fk_person")
    private Person person;

    @OneToOne(mappedBy = "job", cascade = CascadeType.ALL)
    private Location location;

    // Getters and Setters
}
