package com.example.examsystem.dto;

import com.example.examsystem.bean.Question;
import lombok.Data;


@Data
public class QuestionDto extends Question
{
	private String answer;
}
