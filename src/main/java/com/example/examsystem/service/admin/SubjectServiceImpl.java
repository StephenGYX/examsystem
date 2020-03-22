package com.example.examsystem.service.admin;

import com.example.examsystem.bean.Subject;
import com.example.examsystem.bean.TableModel;
import com.example.examsystem.dao.SubjectDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubjectServiceImpl implements SubjectService
{
	@Autowired(required = false)
	private SubjectDao subjectDao;

	@Override
	public String getList(Integer page, Integer limit)
	{
		TableModel tableModel=new TableModel();
		tableModel.setCode(0);
		tableModel.setMsg("");
		tableModel.setCount(subjectDao.getSum());
		tableModel.setData(subjectDao.getList(limit,limit*(page-1)));
		Gson g=new Gson();
		return g.toJson(tableModel);
	}

	@Override
	public String checkSubject(String sname)
	{
		String str="no";
		Subject subject=subjectDao.getSubject(sname);
		if(subject==null)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String addSubject(String snaeme)
	{
		String str="no";
		int n=subjectDao.addSubject(snaeme);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}
}
