package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Manager implements Serializable {

    private String maccount;
    private String mpassword;

}
