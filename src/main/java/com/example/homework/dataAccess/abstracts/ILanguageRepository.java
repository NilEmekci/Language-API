package com.example.homework.dataAccess.abstracts;

import com.example.homework.entities.Language;

import java.util.List;

public interface ILanguageRepository {


    void delete(String id);
    Language add(Language language);
    Language updateName(Language language, String id);
    List<Language> getAll();
    Language getById(String id);
    Language getByName(String name);




}
