package com.example.language.controller;


import com.example.language.dto.TechnologyRequest;
import com.example.language.dto.TechnologyResponse;
import com.example.language.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/technology")
@RequiredArgsConstructor
public class TechnologyController {


    private final TechnologyService technologyService;


    @GetMapping("/getAll")
    public List<TechnologyResponse> getAll() {
        return technologyService.getAll();
    }

    @PostMapping("/add")
    public TechnologyResponse add(@Valid  @RequestBody TechnologyRequest technologyRequest ) {
        return technologyService.add(technologyRequest);
    }

    @PutMapping("/update/{id}")
    public TechnologyResponse update(@Valid @RequestBody TechnologyRequest technologyRequest, @PathVariable int id) {
        return technologyService.update(technologyRequest , id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@Valid @PathVariable int id) {
        technologyService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public TechnologyResponse getById(@Valid @PathVariable int id) {
        return technologyService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public TechnologyResponse getByName(@Valid @PathVariable String name) {
        return technologyService.getByName(name);
    }



}
