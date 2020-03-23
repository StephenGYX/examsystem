package com.example.examsystem.dto;

import com.example.examsystem.bean.Studentexam;
import lombok.Data;

@Data
public class ExamDto extends Studentexam
{
	private String tname;
	private String sname;
}
