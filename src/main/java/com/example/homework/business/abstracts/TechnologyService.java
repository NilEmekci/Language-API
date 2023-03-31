package com.example.homework.business.abstracts;

import com.example.homework.business.concretes.TechnologyManager;
import com.example.homework.business.request.TechnologyRequest;
import com.example.homework.business.response.TechnologyResponse;
import com.example.homework.entities.Technology;

import java.util.List;

public interface TechnologyService {

     TechnologyResponse add(TechnologyRequest technologyRequest);

     TechnologyResponse update(TechnologyRequest technologyRequest, int id);

     void delete(int id);

     TechnologyResponse getById(int id);

     TechnologyResponse getByName(String name);

     List<TechnologyResponse> getAll();








}
