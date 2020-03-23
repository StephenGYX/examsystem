package com.example.examsystem.service.student;

import com.example.examsystem.bean.Subject;
import com.example.examsystem.dao.StudentScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
