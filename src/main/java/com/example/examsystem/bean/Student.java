package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private String sid;
    private String saccount;
    private String spassword;
    private String sname;
    private String ssex;
    private String sbirth;
    private String sclass;
    private String sregtime;
}
