package com.example.language.repository;

import com.example.language.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology,Integer> {

    Optional<Technology> findByName(String name);

    }

