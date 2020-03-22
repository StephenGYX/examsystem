package com.example.examsystem.controller.adminController;

import com.example.examsystem.service.admin.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/subject/")
public class SubjectController
{
	@Autowired
	private SubjectService subjectServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public String getList(String page,String limit)
	{
		return subjectServiceImpl.getList(Integer.valueOf(page),Integer.valueOf(limit));
	}

	@RequestMapping("checkSubject")
	@ResponseBody
	public String checkSubject(String sname)
	{
		return subjectServiceImpl.checkSubject(sname);
	}

	@RequestMapping("addSubject")
	@ResponseBody
	public String addSubject(String sname)
	{
		return subjectServiceImpl.addSubject(sname);
	}

	@RequestMapping("toSubject")
	public String toSubject()
	{
		return "admin/subjectTable";
	}
}