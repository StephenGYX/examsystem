package com.example.examsystem.dao;

import com.example.examsystem.bean.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StudentScoreDao
{
	//查看科目类型
	public List<Subject> searchType();
}
