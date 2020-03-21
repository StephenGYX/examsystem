package com.example.examsystem.dto;

import com.example.examsystem.bean.Question;
import lombok.Data;

@Data
public class TopicDto extends Question
{
	private String sname;
}
