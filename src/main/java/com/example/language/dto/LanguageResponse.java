package com.example.language.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageResponse {


    private int id;
    private String name;

    /*private List<String> technologieName;*/
}
