package com.example.examsystem.service.student;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.bean.Subject;
import com.example.examsystem.dao.StudentScoreDao;
import com.example.examsystem.dto.ExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 查看分数及考试详情
 */
@Service
public class ExamScoreServiceImpl implements ExamScoreService
{
	@Autowired
	private StudentScoreDao studentScoreDao;

	/**
	 * 查看科目类型
	 * @return
	 */
	@Override
	public List<Subject> searchType()
	{
		return studentScoreDao.searchType();
	}

	/**
	 * 查看考试成绩表格
	 * @param request
	 * @return
	 */
	@Override
	public Results<ExamDto> testTable(HttpSession session, PageTableRequest request)
	{
		request.countOffset();
		Student student=(Student)session.getAttribute("student");
		return Results.success(studentScoreDao.testTableCount(student.getSid()+""),studentScoreDao.testTable(student.getSid(),request.getOffset(),request.getLimit()));
	}
}
