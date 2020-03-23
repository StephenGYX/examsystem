package com.example.examsystem.dao;

import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Subject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamQuestionDao
{
	@Select("<script> SELECT * FROM exam WHERE tid=#{tid} " +
			"<when test='sub!=null'> AND sid=#{sub}</when>" +
			"LIMIT #{limit} OFFSET #{end} </script>")
	public abstract List<Exam> getList(Long tid,Integer limit,Integer end,Long sub);

	@Select("<script> SELECT COUNT(*) FROM exam WHERE tid=#{tid}" +
			"<when test='sub!=null'> AND sid=#{sub}</when> </script>")
	public abstract int getSum(Long tid,Long sub);

	@Select("SELECT sname FROM subject WHERE sid=#{sid}")
	public abstract String getSubjectName(Long sid);

	@Select("SELECT sid FROM teachersubject WHERE tid=#{tid}")
	public abstract List<Subject> getSubs(Long tid);

	@Select("SELECT COUNT(*) FROM studentexam WHERE eid=#{eid}")
	public abstract int checkExam(Long eid);

	@Delete("DELETE FROM exam WHERE eid=#{eid}")
	public abstract int deleteExam(Long eid);
}