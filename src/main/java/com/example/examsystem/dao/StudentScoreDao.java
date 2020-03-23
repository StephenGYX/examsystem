package com.example.examsystem.dao;

import com.example.examsystem.bean.Subject;
import com.example.examsystem.dto.ExamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface StudentScoreDao
{
	//查看科目类型
	public List<Subject> searchType();
	//查看考试成绩
	public List<ExamDto> testTable(@Param("sid")String sid,@Param("startP")Integer startP,@Param("endP")Integer endP);
	public int testTableCount(@Param("sid")String sid);
}
