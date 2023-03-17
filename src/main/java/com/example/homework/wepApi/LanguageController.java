package com.example.homework.wepApi;

import com.example.homework.business.abstracts.LanguageService;
import com.example.homework.entities.Language;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //annotation
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;


    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAll")
    public List<Language> getAll() {
        return languageService.getAll();
    }

    @PostMapping("/add")
    public Language add(@RequestBody Language language) {
        return languageService.add(language);
    }

    @PutMapping("/update/{id}")
    public Language updateName(@RequestBody Language language, @PathVariable String id) {
        return languageService.updateName(language , id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        languageService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Language getById(@PathVariable String id) {
       return languageService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public Language getByName(@PathVariable String name) {
        return languageService.getByName(name);
    }


}
