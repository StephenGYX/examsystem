package com.example.examsystem.service.student;

import com.example.examsystem.bean.Answer;
import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.Student;
import com.example.examsystem.dao.StudentExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 学生进行考试
 */
@Service
public class StudentExamServiceImpl implements StudentExamService
{

	@Autowired
	private StudentExamDao studentExamDao;
	/**
	 * 查看试卷
	 * @param examnum
	 * @return
	 */
	@Override
	public List<Question> examQuestion(String examnum,HttpSession session)
	{
		Student student=(Student)session.getAttribute("student");
		int index=studentExamDao.reviewExam(examnum, student.getSid());
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String n=simpleDateFormat.format(new Date());
		Exam exam=studentExamDao.examCode(examnum,n);
		List<Question> list=new ArrayList<>();
		if (index>0)
		{
			Question q=new Question();
			q.setQtime("online");
			list.add(q);
		}else
		{
			if (exam!=null)
			{
				session.setAttribute("exam",exam);
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd hh:mm");
				String dostarttime=sd.format(new Date());
				session.setAttribute("dostarttime",dostarttime);
				list=studentExamDao.examQuestion(Integer.valueOf(exam.getEid()+""));
				if (list.size()>0)
				{
					list.get(0).setCorrect(exam.getEduration());
				}else
				{
					Question q=new Question();
					q.setQtime("notquestion");
					list.add(q);
				}
			}else
			{
				Question q=new Question();
				q.setQtime("error");
				list.add(q);
			}
		}
		return list;
	}

	/**
	 * 考试答题
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String doexam(HttpServletRequest request)
	{
		String res="error";
		Map<String,String[]> map=request.getParameterMap();
		List<Answer> answers=new ArrayList<>();
		Student student=(Student)request.getSession().getAttribute("student");
		Exam exam=(Exam)request.getSession().getAttribute("exam");
		for (Map.Entry<String,String[]> entry:map.entrySet()){
			Answer answer=new Answer();
			answer.setEid(exam.getEid());
			answer.setQid(Long.valueOf(entry.getKey()));
			answer.setAnswer(entry.getValue()[0]);
			answer.setSid(Long.valueOf(student.getSid()));
			Question que=studentExamDao.getType(entry.getKey());
			answer.setQtype(que.getQtype());
			answer.setCorrect(que.getCorrect());
			answers.add(answer);
		}
		int index=studentExamDao.doexam(answers);
		if (index>0)
		{
			SimpleDateFormat ed=new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String doendtime=ed.format(new Date());
			String dostarttime=(String)request.getSession().getAttribute("dostarttime");
			index=studentExamDao.overexam(student.getSid(),exam.getEid()+"",dostarttime,doendtime);
			if (index>0)
			{
				res="success";
			}
		}
		return res;
	}

	/**
	 * 判断学生是否考试结束
	 * @param session
	 * @return
	 */
	@Override
	public String getOnline(HttpSession session)
	{
		Student student=(Student)session.getAttribute("student");
		Exam exam=(Exam)session.getAttribute("exam");
		String res="error";
		if (exam!=null)
		{
			int index=studentExamDao.reviewExam(exam.getEcode(), student.getSid());
			if (index>0)
			{
				res="success";
			}
		}

		return res;
	}


}
