package com.example.examsystem.service.admin;

import javax.servlet.http.HttpServletRequest;

public interface PasswordService
{
	public abstract String updateAdminPassword(String pass);

	public abstract String updateTeacherPassword(String pass, HttpServletRequest request);
}