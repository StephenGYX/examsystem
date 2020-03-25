package com.example.examsystem.dao;

import com.example.examsystem.bean.Exam;
import com.example.examsystem.bean.Subject;
import org.apache.ibatis.annotations.*;

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

	@Select("SELECT COUNT(*) FROM exam WHERE ecode=#{ecode}")
	public abstract int checkCode(String ecode);

	@Select("SELECT COUNT(*) FROM question WHERE qtype=#{type} AND sid=#{sid}")
	public abstract int getQuestSum(Long sid,Integer type);

	@Insert("INSERT INTO exam (sid,tid,ename,ecode,estart,eend,eduration,eregtime) " +
			"VALUES (#{sid},#{tid},#{ename},#{ecode},#{estart},#{eend},#{eduration},#{eregtime})")
	@Options(useGeneratedKeys = true, keyProperty = "eid")
	public abstract int addExam(Exam exam);

	@Insert("INSERT INTO examquestion (eid,qid) VALUES (#{eid},#{qid})")
	public abstract int addExamQuestion(Long eid,Long qid);

	@Select("SELECT qid FROM question WHERE qtype=#{type}")
	public abstract List<Long> getQid(Integer type);

	@Delete("DELETE FROM examquestion WHERE eid=#{eid}")
	public abstract int deleteAll(Long eid);
}