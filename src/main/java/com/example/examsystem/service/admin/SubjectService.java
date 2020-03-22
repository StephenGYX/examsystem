package com.example.examsystem.service.admin;

import com.example.examsystem.bean.Subject;

import java.util.List;

public interface SubjectService
{
	public abstract String getList(Integer page, Integer limit);

	public abstract String checkSubject(String sname);

	public abstract String addSubject(String snaeme);

}