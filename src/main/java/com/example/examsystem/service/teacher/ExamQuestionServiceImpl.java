package com.example.examsystem.service.teacher;

import com.example.examsystem.bean.*;
import com.example.examsystem.dao.ExamQuestionDao;
import com.google.gson.Gson;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
			int s=examQuestionDao.deleteAll(eid);
			if(s>0)
			{
				str="yes";
			}
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

	@Override
	public String addExam2(HttpServletRequest request, Long sid, String estart, String eend, String ename, String ecode, String eduration, QuestModel[] list)
	{
		String str="no";
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		Exam exam=new Exam();
		exam.setEcode(ecode);
		exam.setEduration(eduration);
		exam.setEname(ename);
		exam.setEstart(estart);
		exam.setEend(eend);
		exam.setSid(sid);
		exam.setTid(Long.valueOf(teacher.getTid()));
		exam.setEregtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		examQuestionDao.addExam(exam);

		for (QuestModel questModel : list)
		{
			examQuestionDao.addExamQuestion(exam.getEid(),Long.valueOf(questModel.getValue()));
		}
		str="yes";
		return str;
	}

	@Override
	public String addExam(HttpServletRequest request, Long sid, Integer q1, Integer q2, Integer q3, Integer q4, String estart, String eend, String ename, String ecode, String eduration)
	{
		String str="no";
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		Exam exam=new Exam();
		exam.setEcode(ecode);
		exam.setEduration(eduration);
		exam.setEname(ename);
		exam.setEstart(estart);
		exam.setEend(eend);
		exam.setSid(sid);
		exam.setTid(Long.valueOf(teacher.getTid()));
		exam.setEregtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		examQuestionDao.addExam(exam);

		List<Long> list=examQuestionDao.getQid(1);
		List<Long> list2=examQuestionDao.getQid(2);
		List<Long> list3=examQuestionDao.getQid(3);
		List<Long> list4=examQuestionDao.getQid(4);

		Collections.shuffle(list);
		Collections.shuffle(list2);
		Collections.shuffle(list3);
		Collections.shuffle(list4);

		list=list.subList(0,q1);
		list2=list2.subList(0,q2);
		list3=list3.subList(0,q3);
		list4=list4.subList(0,q4);

		for (Long aLong : list)
		{
			examQuestionDao.addExamQuestion(exam.getEid(),aLong);
		}

		for (Long aLong : list2)
		{
			examQuestionDao.addExamQuestion(exam.getEid(),aLong);
		}

		for (Long aLong : list3)
		{
			examQuestionDao.addExamQuestion(exam.getEid(),aLong);
		}

		for (Long aLong : list4)
		{
			examQuestionDao.addExamQuestion(exam.getEid(),aLong);
		}

		str="yes";
		return str;
	}

	@Override
	public List<QuestModel> getQuestionList(Long sid)
	{
		List<Question> list=examQuestionDao.getQuestionList(sid);
		List<QuestModel> modelList=new ArrayList<>();
		for (Question question : list)
		{
			String str="";
			switch (question.getQtype())
			{
				case 1:
					str="【选择题】";
				break;
				case 2:
					str="【判断题】";
					break;
				case 3:
					str="【填空题】";
					break;
				case 4:
					str="【简答题】";
					break;
			}
			QuestModel qm=new QuestModel();
			qm.setValue(question.getQid()+"");
			qm.setType(question.getQtype()+"");
			qm.setTitle(str+question.getTitle());
			modelList.add(qm);
		}
		return modelList;
	}

	@Override
	public String checkQuest(QuestModel[] list, int q1, int q2, int q3, int q4)
	{

		String str="no";
		int a=0;
		int b=0;
		int c=0;
		int d=0;

		for (QuestModel questModel : list)
		{
			switch (questModel.getType())
			{
				case "1" :
					a++;
					break;
				case "2" :
					b++;
					break;
				case "3" :
					c++;
					break;
				case "4" :
					d++;
					break;
			}
		}
		if(a==q1 && b==q2 && c==q3 && d==q4)
		{
			str="yes";
		}


		return str;
	}

	@Override
	public String check(Long sid, Integer q1, Integer q2, Integer q3, Integer q4, String estart, String ecode)
	{
		String str="time";
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(today.compareTo(estart)<0)
		{
			str="code";
			int n=examQuestionDao.checkCode(ecode);
			if(n==0)
			{
				str="q1";
				int sum=examQuestionDao.getQuestSum(sid,1);
				if(sum>=q1)
				{
					str="q2";
					int sum2=examQuestionDao.getQuestSum(sid,2);
					if(sum2>=q2)
					{
						str="q3";
						int sum3=examQuestionDao.getQuestSum(sid,3);
						if(sum3>=q3)
						{
							str="q4";
							int sum4=examQuestionDao.getQuestSum(sid,4);
							if(sum4>=q4)
							{
								str="yes";
							}
						}
					}
				}
			}
		}

		return str;
	}
}