package com.example.examsystem.dao;

import com.example.examsystem.bean.Subject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface SubjectDao
{
	@Select("SELECT * FROM subject LIMIT #{limit} OFFSET #{end}")
	public abstract List<Subject> getList(Integer limit, Integer end);

	@Select("SELECT COUNT(*) FROM subject")
	public abstract int getSum();

	@Insert("INSERT INTO subject (sname) VALUES (#{sname})")
	public abstract int addSubject(String sname);

	@Select("SELECT * FROM subject WHERE sname=#{sname}")
	public abstract Subject getSubject(String sname);


}