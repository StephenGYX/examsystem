package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Answer implements Serializable
{

  private long aid;
  private long eid;
  private long qid;
  private long sid;
  private String answer;
  private String correct;


}
