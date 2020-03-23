package com.example.examsystem.dao;

import com.example.examsystem.bean.ClassInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassDao
{
	@Select("SELECT * FROM class LIMIT #{limit} OFFSET #{end}")
	public abstract List<ClassInfo> getList(Integer limit, Integer end);

	@Select("SELECT COUNT(*) FROM class")
	public abstract int getSum();

	@Select("SELECT * FROM class WHERE cname=#{cname}")
	public abstract ClassInfo getClassinfo(String cname);

	@Insert("INSERT INTO class (cname) VALUES (#{cname})")
	public abstract int addClass(String cname);

	@Select("SELECT COUNT(*) FROM student WHERE sclass=#{cname}")
	public abstract int getCount(String cname);

	@Delete("delete from class where cname = #{cname}")
    int delClass(String cname);

	@Select("select * from class ")
	List<ClassInfo> getClassAll();
}