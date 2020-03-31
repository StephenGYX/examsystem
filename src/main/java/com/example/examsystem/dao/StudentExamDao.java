package com.example.examsystem.dao;

import com.example.examsystem.bean.Answer;
import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentExamDao
{
	//查看试卷题目
	public List<Question> examQuestion(@Param("eid") Integer eid);
	//查看考试码是否存在
	public Exam examCode(@Param("ecode") String ecode, @Param("nowdate") String nowdate);
	//查看考试码是否存在
	public Exam examCode2(@Param("ecode") String ecode);
	//考试查重
	public int reviewExam(@Param("ecode") String ecode, @Param("sid") String sid);
	//考试提交
	public int doexam(@Param("list")List<Answer> list);
	//考试结束，提交考试关系表
	public int overexam(@Param("eid")String eid,@Param("sid")String sid,@Param("start")String start,@Param("end")String end);
	//获取题目类型
	public Question getType(@Param("qid")String qid);
	//修改密码
	public int changePass(@Param("saccount")String saccount,@Param("spass")String spass);
	//判断出卷考试是否为交给学生的老师
	public List<Integer> verifyT(@Param("sclass")String sclass);
}
