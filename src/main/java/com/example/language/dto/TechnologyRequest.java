package com.example.language.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyRequest {



    @NotBlank(message = "TECHNOLOGY NAME CANNOT BE NOT NULL")
    private String name;


    @NotBlank(message = "LANGUAGE NAME CANNOT BE NOT NULL")
    private String languageName;
}
