package com.example.examsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PasswordDao
{

	@Update("UPDATE manager SET mpassword=#{pass} ")
	public abstract int updateAdminPassword(String pass);

	@Update("UPDATE teacher SET tpassword=#{pass} WHERE tid=#{tid}")
	public abstract int updateTeacherPassword(String pass,Long tid);
}