package com.example.homework.business.abstracts;

import com.example.homework.entities.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getAll();

    Language add(Language language);

    Language updateName(Language language, String id );

    void delete(String id);

    Language getById(String id);

    Language getByName(String name);



}
