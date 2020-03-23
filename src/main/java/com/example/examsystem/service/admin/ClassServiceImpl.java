package com.example.examsystem.service.admin;

import com.example.examsystem.bean.ClassInfo;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.bean.TableModel;
import com.example.examsystem.dao.ClassDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService
{
	@Autowired(required = false)
	private ClassDao classDao;

	@Override
	public String getClassList(Integer page, Integer limit)
	{
		TableModel tableModel=new TableModel();
		tableModel.setCode(0);
		tableModel.setMsg("");
		tableModel.setCount(classDao.getSum());
		List<ClassInfo> list=classDao.getList(limit,limit*(page-1));
		for (ClassInfo classInfo : list)
		{
			classInfo.setCount(classDao.getCount(classInfo.getCname()));
		}
		tableModel.setData(list);
		Gson g=new Gson();
		return g.toJson(tableModel);
	}

	@Override
	public String checkClass(String cname)
	{
		String str="no";
		ClassInfo classInfo=classDao.getClassinfo(cname);
		if(classInfo==null)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String addClass(String cname)
	{
		String str="no";
		int n=classDao.addClass(cname);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String delClass(String cname) {
		String str = "false";
		if (classDao.delClass(cname) > 0){
			str = "yes";
		}

		return str;
	}

	@Override
	public String getClassAll() {
		List<ClassInfo> list = classDao.getClassAll();
		return new Gson().toJson(list);
	}
}