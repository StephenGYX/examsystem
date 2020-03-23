package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Studentexam implements Serializable
{

  private long seid;
  private long eid;
  private long sid;
  private String starttime;
  private String endtime;
  private String score;

}
