package com.example.homework.wepApi;

import com.example.homework.business.abstracts.LanguageService;
import com.example.homework.business.request.LanguageRequest;
import com.example.homework.business.response.LanguageResponse;
import com.example.homework.entities.Language;
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
    public LanguageResponse add(@RequestBody  LanguageRequest languageRequest) {
        return languageService.add(languageRequest);
    }

    @PutMapping("/update/{id}")
    public LanguageResponse updateName(@RequestBody LanguageRequest languageRequest, @PathVariable int id) {
        return languageService.updateName(languageRequest , id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        languageService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public LanguageResponse getById(@PathVariable int id) {
       return languageService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public LanguageResponse getByName(@PathVariable String name) {
        return languageService.getByName(name);
    }


}
