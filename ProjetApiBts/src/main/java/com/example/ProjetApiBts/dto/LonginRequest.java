package com.example.ProjetApiBts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class LonginRequest {
    private  String email;
    private String password;

}
