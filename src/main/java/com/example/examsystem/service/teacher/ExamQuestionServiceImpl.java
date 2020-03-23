package com.example.examsystem.service.teacher;

import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.bean.TableModel;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.dao.ExamQuestionDao;
import com.google.gson.Gson;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService
{
	@Autowired(required = false)
	private ExamQuestionDao examQuestionDao;

	@Override
	public List<Subject> getSubs(HttpServletRequest request)
	{
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		List<Subject> list=examQuestionDao.getSubs(Long.valueOf(teacher.getTid()));
		for (Subject subject : list)
		{
			subject.setSname(examQuestionDao.getSubjectName(Long.valueOf(subject.getSid())));
		}
		return list;
	}

	@Override
	public String checkExam(Long eid)
	{
		String str="no";
		int n=examQuestionDao.checkExam(eid);
		if(n==0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String deleteExam(Long eid)
	{
		String str="no";
		int n=examQuestionDao.deleteExam(eid);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String getMyExams(Integer limit, Integer page, String sub,HttpServletRequest request)
	{
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		Long s=null;
		if(sub!=null && !(sub.equals("所有")))
		{
			s=Long.valueOf(sub);
		}
		TableModel tableModel=new TableModel();
		tableModel.setMsg("");
		tableModel.setCode(0);
		tableModel.setCount(examQuestionDao.getSum(Long.valueOf(teacher.getTid()),s));
		List<Exam> list=examQuestionDao.getList(Long.valueOf(teacher.getTid()),limit,limit*(page-1),s);
		for (Exam exam : list)
		{
			exam.setTips(examQuestionDao.getSubjectName(exam.getSid()));
		}
		tableModel.setData(list);
		Gson g=new Gson();
		return g.toJson(tableModel);
	}
}