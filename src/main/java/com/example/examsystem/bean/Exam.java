package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Exam  implements Serializable
{

  private long eid;
  private long tid;
  private long sid;
  private String ename;
  private String estart;
  private String eend;
  private String eduration;
  private String ecode;
  private String eregtime;
  private String tips;


}
