package com.example.examsystem.service.admin;

import com.example.examsystem.bean.Teacher;
import com.example.examsystem.dao.PasswordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordServiceImpl implements PasswordService
{
	@Autowired(required = false)
	private PasswordDao passwordDao;

	@Override
	public String updateAdminPassword(String pass)
	{
		String str="no";
		int n=passwordDao.updateAdminPassword(pass);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String updateTeacherPassword(String pass, HttpServletRequest request)
	{
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		String str="no";
		int n=passwordDao.updateTeacherPassword(pass,Long.valueOf(teacher.getTid()));
		if(n>0)
		{
			str="yes";
		}
		return str;
	}
}