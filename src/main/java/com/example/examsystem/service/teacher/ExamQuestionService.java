package com.example.examsystem.service.teacher;

import com.example.examsystem.bean.QuestModel;
import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface ExamQuestionService
{
	public abstract String getMyExams(Integer limit, Integer page, String sub,HttpServletRequest request);

	public abstract List<Subject> getSubs(HttpServletRequest request);

	public abstract String checkExam(Long eid);

	public abstract String deleteExam(Long eid);

	public String check(Long sid,Integer q1,Integer q2,Integer q3,Integer q4,String estart,String ecode);

	public String addExam(HttpServletRequest request,Long sid,Integer q1,Integer q2,Integer q3,Integer q4,String estart,String eend,String ename,String ecode,String eduration);

	public String addExam2(HttpServletRequest request,Long sid,String estart,String eend,String ename,String ecode,String eduration,QuestModel[] list);

	public abstract List<QuestModel> getQuestionList(Long sid);

	public abstract String checkQuest(QuestModel[] list, int q1, int q2, int q3, int q4);
}