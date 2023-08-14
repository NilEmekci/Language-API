package com.example.language.repository;

import com.example.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    Optional<Language> findByName(String name);
}
