package com.example.language.controller;

import com.example.language.business.abstracts.LanguageService;
import com.example.language.business.request.LanguageRequest;
import com.example.language.business.response.LanguageResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //annotation
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;


    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAll")
    public List<LanguageResponse> getAll() {
        return languageService.getAll();
    }

    @PostMapping("/add")
    public LanguageResponse add(@Valid @RequestBody  LanguageRequest languageRequest) {

        return languageService.add(languageRequest);
    }

    @PutMapping("/update/{id}")
    public LanguageResponse updateName(@Valid @RequestBody LanguageRequest languageRequest,@PathVariable int id) {

        return languageService.updateName(languageRequest , id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable  int id) {
        languageService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public LanguageResponse getById(@PathVariable int id) {
       return languageService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public LanguageResponse getByName(@Valid @PathVariable String name) {
        return languageService.getByName(name);
    }


}
