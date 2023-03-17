package com.example.homework.business.concretes;

import com.example.homework.business.abstracts.LanguageService;
import com.example.homework.dataAccess.abstracts.ILanguageRepository;
import com.example.homework.entities.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {


    private final ILanguageRepository iLanguageRepository;

    public LanguageManager(ILanguageRepository iLanguageRepository) {
        this.iLanguageRepository = iLanguageRepository;
    }

    @Override
    public List<Language> getAll() {
        return iLanguageRepository.getAll();
    }

    @Override
    public Language add(Language language) {
        Language languageToAdd = iLanguageRepository.getById(language.getId());
        if (languageToAdd == null && isEmpty(language.getName()) && !isRepeat(language.getName())){
            return iLanguageRepository.add(language);
        }
         else throw new RuntimeException(" Id is invalid or name space cannot be empty ");
    }

    @Override
    public Language updateName(Language language, String id) {
        Language languageToUpdate = iLanguageRepository.getById(id);
         if (languageToUpdate == null){
             throw new RuntimeException("User does not exist");
         }
        return iLanguageRepository.updateName(language, id);
    }

    @Override
    public void delete( String id) {
        Language languageToDelete = iLanguageRepository.getById(id);
        if (languageToDelete == null){
            throw new RuntimeException("User does not exist");
        }
        iLanguageRepository.delete( id);
    }

    @Override
    public Language getById(String id){
       return iLanguageRepository.getById(id);
    }

    @Override
    public Language getByName(String name){
        return iLanguageRepository.getByName(name);
    }

    private boolean isEmpty(String name){
        Language languageToIsEmpty = iLanguageRepository.getByName(name);
        if (languageToIsEmpty == null){
             return true;
        }
        else return false;
    }

    private boolean isRepeat(String name){
        Language languageToIsRepeat = iLanguageRepository.getByName(name);
        if (languageToIsRepeat.getName().equals(name)){
            return true;
        }
        else return false;
    }


}
