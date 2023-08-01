package com.example.homework.dataAccess.abstracts;

import com.example.homework.Model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology,Integer> {

    Optional<Technology> findByName(String name);

    }

