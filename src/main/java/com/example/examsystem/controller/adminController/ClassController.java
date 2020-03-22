package com.example.examsystem.controller.adminController;

import com.example.examsystem.service.admin.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/class/")
public class ClassController
{
	@Autowired
	private ClassService classServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public String getList(String page,String limit)
	{
		return classServiceImpl.getClassList(Integer.valueOf(page),Integer.valueOf(limit));
	}

	@RequestMapping("checkClass")
	@ResponseBody
	public String checkClass(String cname)
	{
		return classServiceImpl.checkClass(cname);
	}

	@RequestMapping("addClass")
	@ResponseBody
	public String addClass(String cname)
	{
		return classServiceImpl.addClass(cname);
	}

	@RequestMapping("toClass")
	public String toClass()
	{
		return "admin/classTable";
	}


	@RequestMapping("delClass")
	@ResponseBody
	public String delClass(String cname)
	{
		return classServiceImpl.delClass(cname);
	}
}