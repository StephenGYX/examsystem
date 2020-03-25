package com.example.examsystem.bean;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;

import java.io.Serializable;

@Data
public class QuestModel implements Serializable
{
	private String value;
	private String title;
	private String type;
}