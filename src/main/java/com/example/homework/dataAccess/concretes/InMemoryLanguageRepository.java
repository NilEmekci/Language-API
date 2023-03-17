package com.example.homework.dataAccess.concretes;

import com.example.homework.dataAccess.abstracts.ILanguageRepository;
import com.example.homework.entities.Language;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryLanguageRepository implements ILanguageRepository{

    List<Language> languages;

    public InMemoryLanguageRepository(){
        languages = new ArrayList<Language>();
        languages.add(new Language("Java","11"));
        languages.add(new Language("Python","12"));
        languages.add(new Language("C#","13"));


    }

    @Override
    public void delete(String id) {
        for(int i=0; i<languages.size();i++){
            if(languages.get(i).getId().equals(id)){
                languages.remove(i);
            }
        }
    }

    @Override
    public Language add(Language language) {
        languages.add(languages.size(),language);
        return language;
    }

    @Override
    public Language updateName(Language language, String id) {

        for(int i=0; i<languages.size();i++){
            if(languages.get(i).getId().equals(id)){
                languages.get(i).setName(language.getName());
                return languages.get(i);

            }
        }
        return null;
    }

    @Override
    public List<Language> getAll() {
        return languages;
    }


    @Override
    public Language getById(String id){

        for(int i=0; i<languages.size();i++){
            if(languages.get(i).getId().equals(id)){
                return languages.get(i);
            }
        }
        return null;
    }

    @Override
    public Language getByName(String name){

        for(int i=0; i<languages.size();i++){
            if(languages.get(i).getName().equals(name)){
                return languages.get(i);
            }
        }
        return null;
    }


}
