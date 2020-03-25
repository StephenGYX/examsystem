package com.example.examsystem.controller.teachercontroller;

import com.example.examsystem.bean.QuestModel;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.service.teacher.ExamQuestionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

	@RequestMapping("checkQuest")
	@ResponseBody
	public String checkQuest(String list,int q1,int q2,int q3,int q4)
	{
		Gson g=new Gson();
		QuestModel[] arr=g.fromJson(list, QuestModel[].class);
		return examQuestionServiceImpl.checkQuest(arr, q1, q2, q3, q4);
	}

	@RequestMapping("getQuestionList")
	@ResponseBody
	public List<QuestModel> getQuestionList(Long sid)
	{
		return examQuestionServiceImpl.getQuestionList(sid);
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

	@RequestMapping("addExam2")
	@ResponseBody
	public String addExam2(HttpServletRequest request, Long sid,  String estart, String eend, String ename, String ecode, String eduration,String list)
	{
		Gson g=new Gson();
		QuestModel[] arr=g.fromJson(list, QuestModel[].class);
		return examQuestionServiceImpl.addExam2(request, sid,  estart, eend, ename, ecode, eduration,arr);
	}

	@RequestMapping("getSubs")
	@ResponseBody
	public List<Subject> getSubs(HttpServletRequest request)
	{
		return examQuestionServiceImpl.getSubs(request);
	}
}