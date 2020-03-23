package com.example.examsystem.dao;

import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentExamDao
{
	//查看试卷题目
	public List<Question> examQuestion(@Param("eid") Integer eid);
	//查看考试码是否存在
	public Exam examCode(@Param("ecode") String ecode, @Param("nowdate") String nowdate);
	//考试查重
	public int reviewExam(@Param("ecode") String ecode, @Param("sid") String sid);
}
