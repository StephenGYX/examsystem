package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {

    private String tid;
    private String taccount;
    private String tpassword;
    private String tname;
    private String tsex;
    private String tbirth;
    private String teducation;
    private String tregtime;
    private String[] tsubject;
    private String[] tclass;
}
