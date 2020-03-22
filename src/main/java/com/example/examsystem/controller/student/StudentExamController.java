package com.example.examsystem.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是考试页面的控制层
 */

@Controller
@RequestMapping("/studentexam")
public class StudentExamController
{
	/**
	 * 跳转考试页面
	 * @return
	 */
	@RequestMapping("/exammain")
	public String exammain(){
		return "student/studentexam";
	}

	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/tologin")
	public String tologin(){
		return "index";
	}
}
