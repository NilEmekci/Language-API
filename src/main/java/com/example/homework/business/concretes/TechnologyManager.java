package com.example.homework.business.concretes;

import com.example.homework.business.abstracts.TechnologyService;
import com.example.homework.business.request.TechnologyRequest;
import com.example.homework.business.response.TechnologyResponse;
import com.example.homework.dataAccess.abstracts.LanguageRepository;
import com.example.homework.dataAccess.abstracts.TechnologyRepository;
import com.example.homework.Model.Language;
import com.example.homework.Model.Technology;
import com.example.homework.exception.EntityAlreadyException;
import com.example.homework.exception.EntityNotFoundException;
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

        languageRepository.findByName(technologyRequest.getLanguageName()).orElseThrow(() -> new EntityNotFoundException("Language not found "));
        technologyRepository.findByName(technologyRequest.getName()).ifPresent(l -> { throw new EntityAlreadyException("Technology already exists with this name"); });

        TechnologyResponse responseItem = new TechnologyResponse();
        responseItem.setName(technologyRequest.getName());
        responseItem.setLanguageName(technologyRequest.getLanguageName());

        Technology technologyToAdd = new Technology();
        technologyToAdd.setName(technologyRequest.getName());

        Language technologyRequestLanguageName = languageRepository.findByName(technologyRequest.getLanguageName()).get();
        technologyToAdd.setLanguage(technologyRequestLanguageName);

        technologyRepository.save(technologyToAdd);
        return responseItem;
    }

    @Override
    public TechnologyResponse update(TechnologyRequest technologyRequest, int id) {

        technologyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Technology not found this id"));
        languageRepository.findByName(technologyRequest.getLanguageName()).orElseThrow(() -> new EntityNotFoundException("Language name not found "));
        technologyRepository.findByName(technologyRequest.getName()).ifPresent(l -> { throw new EntityAlreadyException("Technology already exists with this name"); });

        Technology technologyToUpdate = technologyRepository.findById(id).get();
        technologyToUpdate.setName(technologyRequest.getName());
        technologyRepository.save(technologyToUpdate);

        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setId(technologyToUpdate.getId());
        technologyResponse.setName(technologyToUpdate.getName());
        technologyResponse.setLanguageName(technologyRequest.getLanguageName());

        return technologyResponse;
    }

    @Override
    public void delete(int id) {

        Technology technologyToDelete = technologyRepository.getById(id);
        if (!technologyRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException(" Technology not found this id");
        }
        technologyRepository.deleteById(id);
    }

    @Override
    public TechnologyResponse getById(int id) {
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Technology not found this id"));
        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setId(id);
        technologyResponse.setName(technology.getName());
        technologyResponse.setLanguageName(technology.getLanguage().getName());
        return technologyResponse;
    }

    @Override
    public TechnologyResponse getByName(String name) {
        Technology technology = technologyRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Technology not found this name"));
        TechnologyResponse technologyResponse = new TechnologyResponse();
        technologyResponse.setName(name);
        technologyResponse.setId(technology.getId());
        technologyResponse.setLanguageName(technology.getLanguage().getName());
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
            responseItem.setLanguageName(technology.getLanguage().getName());
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
