package com.example.homework.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRequest {


    @NotBlank(message = "CANNOT BE NOT NULL")
    private String name;
}
