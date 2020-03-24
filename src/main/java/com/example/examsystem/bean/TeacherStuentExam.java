package com.example.examsystem.bean;

import lombok.Data;

@Data
public class TeacherStuentExam
{
	private Integer eid;
	private Integer sid;
	private Integer studentId;
	private Integer seid;
	private String sname;
	private String ename;
	private String ecode;
	private String score;
	private String starttime;
	private String endtime;
}
