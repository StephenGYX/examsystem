package com.example.examsystem.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 考生查看成绩的控制层
 */
@Controller
@RequestMapping("/examscore")
public class ExamscoreController
{
	/**
	 * 跳转查看成绩页面
	 * @return
	 */
	@RequestMapping("/scoremain")
	public String scoremain(){
		return "student/examscore";
	}
}
