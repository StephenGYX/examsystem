package com.example.examsystem.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassInfo implements Serializable
{
	private long cid;
	private String cname;
	private int count;
}