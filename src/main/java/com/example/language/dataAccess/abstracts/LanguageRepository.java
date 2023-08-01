package com.example.language.dataAccess.abstracts;

import com.example.language.Model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    Optional<Language> findByName(String name);
}
