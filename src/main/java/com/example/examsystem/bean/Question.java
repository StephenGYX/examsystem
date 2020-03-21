package com.example.examsystem.bean;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class Question implements Serializable
{
	private Integer qid;
	private Integer sid;
	private Integer qtype;
	private String title;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correct;
	private String qtime;


}
