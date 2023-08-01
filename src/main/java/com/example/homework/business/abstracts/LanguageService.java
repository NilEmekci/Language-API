package com.example.homework.business.abstracts;

import com.example.homework.business.request.LanguageRequest;
import com.example.homework.business.response.LanguageResponse;

import java.util.List;

public interface LanguageService {

    List <LanguageResponse>  getAll();

    LanguageResponse add(LanguageRequest languageRequest);

    LanguageResponse updateName(LanguageRequest languageRequest, int id );

    void delete(int id);

    LanguageResponse getById(int id);

    LanguageResponse getByName(String name);



}
