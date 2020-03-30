package com.example.examsystem.controller.adminController;

import com.example.examsystem.bean.TableModel;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.service.admin.PasswordService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pass/")
public class PasswordController
{
	@Autowired
	private PasswordService passwordServiceImpl;

	@RequestMapping("updateAdminPassword")
	@ResponseBody
	public String updateAdminPassword(String pass)
	{
		return passwordServiceImpl.updateAdminPassword(pass);
	}

	@RequestMapping("updateTeacherPassword")
	@ResponseBody
	public String updateTeacherPassword(String pass,HttpServletRequest request)
	{
		return passwordServiceImpl.updateTeacherPassword(pass, request);
	}

	@RequestMapping("getList")
	@ResponseBody
	public String getList(HttpServletRequest request)
	{
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		TableModel tableModel=new TableModel();
		tableModel.setMsg("");
		tableModel.setCode(0);
		tableModel.setCount(1);
		List<Teacher> list=new ArrayList<>();
		list.add(teacher);
		tableModel.setData(list);
		Gson gson=new Gson();
		return gson.toJson(tableModel);
	}
}