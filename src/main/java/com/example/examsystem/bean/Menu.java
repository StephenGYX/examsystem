package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {

    private String pname;
    private String name;
    private String url;

}
