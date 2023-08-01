package com.example.language.business.abstracts;

import com.example.language.business.request.TechnologyRequest;
import com.example.language.business.response.TechnologyResponse;

import java.util.List;

public interface TechnologyService {

     TechnologyResponse add(TechnologyRequest technologyRequest);

     TechnologyResponse update(TechnologyRequest technologyRequest, int id);

     void delete(int id);

     TechnologyResponse getById(int id);

     TechnologyResponse getByName(String name);

     List<TechnologyResponse> getAll();








}
