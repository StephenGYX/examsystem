package com.example.examsystem.controller.teachercontroller;

import com.example.examsystem.bean.Subject;
import com.example.examsystem.service.teacher.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/eq/")
public class ExamQuestionController
{
	@Autowired
	private ExamQuestionService examQuestionServiceImpl;



	@RequestMapping("getList")
	@ResponseBody
	public String getList(String page, String limit,String sub, HttpServletRequest request)
	{
		return examQuestionServiceImpl.getMyExams(Integer.valueOf(limit),Integer.valueOf(page),sub,request);
	}

	@RequestMapping("checkExam")
	@ResponseBody
	public String checkExam(String eid)
	{
		return examQuestionServiceImpl.checkExam(Long.valueOf(eid));
	}

	@RequestMapping("deleteExam")
	@ResponseBody
	public String deleteExam(String eid)
	{
		return examQuestionServiceImpl.deleteExam(Long.valueOf(eid));
	}

	@RequestMapping("check")
	@ResponseBody
	public String check(Long sid, Integer q1, Integer q2, Integer q3, Integer q4, String estart, String ecode)
	{
		return examQuestionServiceImpl.check(sid, q1, q2, q3, q4, estart, ecode);
	}

	@RequestMapping("addExam")
	@ResponseBody
	public String addExam(HttpServletRequest request, Long sid, Integer q1, Integer q2, Integer q3, Integer q4, String estart, String eend, String ename, String ecode, String eduration)
	{
		return examQuestionServiceImpl.addExam(request, sid, q1, q2, q3, q4, estart, eend, ename, ecode, eduration);
	}

	@RequestMapping("getSubs")
	@ResponseBody
	public List<Subject> getSubs(HttpServletRequest request)
	{
		return examQuestionServiceImpl.getSubs(request);
	}
}