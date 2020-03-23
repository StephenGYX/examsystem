package com.example.examsystem.service.teacher;

import com.example.examsystem.bean.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExamQuestionService
{
	public abstract String getMyExams(Integer limit, Integer page, String sub,HttpServletRequest request);

	public abstract List<Subject> getSubs(HttpServletRequest request);

	public abstract String checkExam(Long eid);

	public abstract String deleteExam(Long eid);

}