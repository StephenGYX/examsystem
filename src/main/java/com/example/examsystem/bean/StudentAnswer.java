package com.example.examsystem.bean;

import lombok.Data;

@Data
public class StudentAnswer
{
	private Integer aid;
	private Integer eid;
	private Integer studentId;
	private String answer;
	private String correct;
	private Integer qtype;
	private String title;
}
