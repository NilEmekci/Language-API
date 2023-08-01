package com.example.homework.business.concretes;

import com.example.homework.business.abstracts.LanguageService;
import com.example.homework.business.request.LanguageRequest;
import com.example.homework.business.response.LanguageResponse;
import com.example.homework.dataAccess.abstracts.LanguageRepository;
import com.example.homework.dataAccess.abstracts.TechnologyRepository;
import com.example.homework.Model.Language;
import com.example.homework.exception.EntityAlreadyException;
import com.example.homework.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageManager implements LanguageService {

    private final LanguageRepository languageRepository;
    private final TechnologyRepository technologyRepository;

    public LanguageManager(LanguageRepository languageRepository, TechnologyRepository technologyRepository) {
        this.languageRepository = languageRepository;
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<LanguageResponse> getAll() {

        List<Language> languages = languageRepository.findAll();
        List<LanguageResponse> languageResponse = new ArrayList<>();
        for (Language language : languages) {
            LanguageResponse responseItem = new LanguageResponse();
            responseItem.setId(language.getId());
            responseItem.setName(language.getName());

            languageResponse.add(responseItem);
        }
        return languageResponse;
    }

    @Override
    public LanguageResponse add(LanguageRequest languageRequest) {

        languageRepository.findByName(languageRequest.getName()).ifPresent(l -> { throw new EntityAlreadyException("Language already exists with this name"); });
            LanguageResponse responseItem = new LanguageResponse();
            responseItem.setName(languageRequest.getName());
            Language languageToAdd =new Language();
            languageToAdd.setName(languageRequest.getName());
            languageRepository.save(languageToAdd);
            return responseItem;
    }

    @Override
    public LanguageResponse updateName(LanguageRequest languageRequest, int id) {


        Language language =languageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found at this id"));
        languageRepository.findByName(languageRequest.getName()).ifPresent(l -> { throw new EntityAlreadyException("Language already exists with this name"); });
        Language languageToUpdate = languageRepository.findById(id).get();
        languageToUpdate.setName(languageRequest.getName());
        languageRepository.save(languageToUpdate);

        LanguageResponse responseItem =new LanguageResponse();
        responseItem.setId(languageToUpdate.getId());
        responseItem.setName(languageToUpdate.getName());

        return responseItem;
    }

    @Override
    public void delete(int id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found at this id"));
          Language languageToDelete = languageRepository.getById(id);
        if (languageToDelete == null) {
            throw new RuntimeException("User does not exist");
        }
        languageRepository.deleteById(id);
    }

    @Override
    public LanguageResponse getById(int id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setId(id);
        languageResponse.setName(language.getName());
        return languageResponse;
    }

    @Override
    public LanguageResponse getByName(String name) {
        Language language = languageRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Entity not found at this name"));
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setName(name);
        languageResponse.setId(language.getId());
        return languageResponse;
    }

    private boolean isPresent(String name) {
        return languageRepository.findByName(name).isPresent();
    }

    private boolean isEmpty(String name) {
        return languageRepository.findByName(name).isEmpty();
    }


}
