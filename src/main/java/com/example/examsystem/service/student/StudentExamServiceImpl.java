package com.example.examsystem.service.student;

import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.Student;
import com.example.examsystem.dao.StudentExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
				list=studentExamDao.examQuestion(Integer.valueOf(exam.getEid()+""));
			}else
			{
				Question q=new Question();
				q.setQtime("error");
				list.add(q);
			}
		}
		return list;
	}


}
