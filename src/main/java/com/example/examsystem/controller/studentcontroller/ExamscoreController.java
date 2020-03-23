package com.example.examsystem.controller.studentcontroller;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.dto.ExamDto;
import com.example.examsystem.service.student.ExamScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 考生查看成绩的控制层
 */
@Controller
@RequestMapping("/examscore")
public class ExamscoreController
{
	@Autowired
	private ExamScoreService examScoreServiceImpl;

	/**
	 * 跳转查看成绩页面
	 * @return
	 */
	@RequestMapping("/scoremain")
	public String scoremain(){
		return "student/examscore";
	}


	/**
	 *  查看试卷科目类型
	 * @return
	 */
	@GetMapping("/searchType")
	@ResponseBody
	public List<Subject> searchType(){
		return examScoreServiceImpl.searchType();
	}


	@RequestMapping("/testTable")
	@ResponseBody
	public Results<ExamDto> testTable(HttpSession session, PageTableRequest request){
		return examScoreServiceImpl.testTable(session,request);
	}
}
