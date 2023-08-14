package com.example.language.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="language")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name="name")
    private String name;


  @OneToMany(
            mappedBy = "language",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Technology> technologies = new ArrayList<>();






}
