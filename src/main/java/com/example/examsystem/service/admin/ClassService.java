package com.example.examsystem.service.admin;

public interface ClassService
{
	public abstract String getClassList(Integer page, Integer limit);

	public abstract String checkClass(String cname);

	public abstract String addClass(String cname);

    String delClass(String cname);

    String getClassAll();
}