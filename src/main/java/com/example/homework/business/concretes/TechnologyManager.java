package com.example.homework.business.concretes;

import com.example.homework.business.abstracts.TechnologyService;
import com.example.homework.business.request.LanguageRequest;
import com.example.homework.business.request.TechnologyRequest;
import com.example.homework.business.response.LanguageResponse;
import com.example.homework.business.response.TechnologyResponse;
import com.example.homework.dataAccess.abstracts.LanguageRepository;
import com.example.homework.dataAccess.abstracts.TechnologyRepository;
import com.example.homework.entities.Language;
import com.example.homework.entities.Technology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {

    private final LanguageRepository languageRepository;
    private final TechnologyRepository technologyRepository;


    public TechnologyManager(LanguageRepository languageRepository, TechnologyRepository technologyRepository) {
        this.languageRepository = languageRepository;
        this.technologyRepository = technologyRepository;
    }

    @Override
    public TechnologyResponse add(TechnologyRequest technologyRequest) {

        boolean languageIsPresent =languageRepository.findByName(technologyRequest.getLanguageName()).isPresent();
        boolean technologyIsPresent =technologyRepository.findByName(technologyRequest.getName()).isPresent();

        if ( technologyIsPresent || !languageIsPresent) {

             throw new RuntimeException(" Language name do not exist or technology is already exist  ");

        }
        TechnologyResponse responseItem = new TechnologyResponse();
        responseItem.setName(technologyRequest.getName());
        responseItem.setTechnologiesLanguageName(technologyRequest.getLanguageName());

        Technology technologyToAdd = new Technology();
        technologyToAdd.setName(technologyRequest.getName());

        Language technologyRequestLanguageName = languageRepository.findByName(technologyRequest.getLanguageName()).get();
        technologyToAdd.setLanguage(technologyRequestLanguageName);

        technologyRepository.save(technologyToAdd);
        return responseItem;
    }

    @Override
    public TechnologyResponse update(TechnologyRequest technologyRequest, int id) {
        Technology technologyToUpdate = technologyRepository.findById(id).get();
        boolean languageIsPresent =languageRepository.findByName(technologyRequest.getLanguageName()).isPresent();

        if (isPresent(technologyRequest.getName()) || !technologyRepository.findById(id).isPresent() || !languageIsPresent) {
            throw new RuntimeException("User does not exist");
        }
        technologyToUpdate.setName(technologyRequest.getName());
        technologyRepository.save(technologyToUpdate);

        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setId(technologyToUpdate.getId());
        technologyResponse.setName(technologyToUpdate.getName());
        technologyResponse.setTechnologiesLanguageName(technologyRequest.getLanguageName());

        return technologyResponse;
    }

    @Override
    public void delete(int id) {
        Technology technologyToDelete = technologyRepository.getById(id);
        if (technologyToDelete == null) {
            throw new RuntimeException(" User does not exist ");
        }
        technologyRepository.deleteById(id);
    }

    @Override
    public TechnologyResponse getById(int id) {
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setId(id);
        technologyResponse.setName(technology.getName());
        technologyResponse.setTechnologiesLanguageName(technology.getLanguage().getName());
        return technologyResponse;
    }

    @Override
    public TechnologyResponse getByName(String name) {
        Technology technology = technologyRepository.findByName(name).orElseThrow(()-> new RuntimeException("Entity not found"));
        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setName(name);
        technologyResponse.setId(technology.getId());
        technologyResponse.setTechnologiesLanguageName(technology.getLanguage().getName());
        return technologyResponse;
    }

    @Override
    public List<TechnologyResponse> getAll() {
        List<Technology> technologies = technologyRepository.findAll();
        List<TechnologyResponse> technologyResponses = new ArrayList<>();
        for (Technology technology : technologies) {
            TechnologyResponse responseItem = new TechnologyResponse();
            responseItem.setId(technology.getId());
            responseItem.setName(technology.getName());
            responseItem.setTechnologiesLanguageName(technology.getName());
            technologyResponses.add(responseItem);
        }

        return technologyResponses;
    }

    private boolean isPresent(String name) {
        return technologyRepository.findByName(name).isPresent();

    }

    private boolean isEmpty(String name) {
        return technologyRepository.findByName(name).isEmpty();

    }

}
