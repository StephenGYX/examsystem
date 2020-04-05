package com.example.examsystem.dao;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.StudentAnswer;
import com.example.examsystem.bean.TeacherStuentExam;
import com.example.examsystem.dto.TopicDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.logging.Level;

@Mapper
public interface TopicDao
{

	@Select("select count(*) from question where qtype=1 and sid=#{sid}")
	Long countAllChooseTopic(String sid);

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=1 and s.sid=#{sid} order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllChooseTopicByPage(Integer startPosition, Integer limit, String sid);

	@Select("select count(*) from question where qtype=3 and sid=#{sid}")
	Long countAllBlankTopic(String sid);

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=3 and q.sid=#{sid} order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllBlankTopicByPage(Integer startPosition, Integer limit, String sid);

	@Select("select count(*) from question where qtype=4 and sid=#{sid}")
	Long countAllShortTopic(String sid);

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=4 and s.sid=#{sid} order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllShortTopicByPage(Integer startPosition, Integer limit, String sid);

	@Select("select count(*) from question where qtype=2 and sid=#{sid}")
	Long countAllJudgeTopic(String sid);

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=2 and s.sid=#{sid} order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllJudgeTopicByPage(Integer startPosition, Integer limit, String sid);

	@Select("select count(*) from question where title=#{titles} and sid=#{sid}")
	int blankTitleQuery(String titles, String sid);

	@Select("select count(*) from question where title=#{title} and sid=#{sid}")
	int chooseTitleQuery(String title, String sid);

	@Insert("insert into question(sid, qtype, title, qtime) values (#{sid},3,#{titles},CURDATE())")
	int blankAdd(String titles, String sid);

	@Insert("insert into question(sid, qtype,option1,option2,option3,option4,correct, title, qtime) values (#{sid},1,#{option1},#{option2},#{option3},#{option4},#{correct},#{title},CURDATE())")
	int chooseAdd(String title, String option1, String option2, String option3, String option4, String correct, String sid);

	@Insert("insert into question(sid, qtype, title, qtime,correct) values (#{sid},2,#{title},CURDATE(),#{correct})")
	int judgeAdd(String title, String correct, String sid);

	@Insert("insert into question(sid, qtype, title, qtime) values (#{sid},4,#{title},CURDATE())")
	int shortAdd(String title, String sid);

	int blankAddMany(List<Question> list);

	int chooseAddMany(List<Question> list);

	int judgeAddMany(List<Question> list);

	@Update("update question set sid=#{question.sid},title=#{question.title},option1=#{question.option1},option2=#{question.option2},option3=#{question.option3},option4=#{question.option4},correct=#{question.correct} where qid=#{question.qid}")
	int topicUpdate(@Param("question") Question question);

	@Select("select count(*) from examquestion where qid=#{qid}")
	int deleteQuery(String qid);

	@Delete("delete from question where qid=#{qid}")
	void delete(String qid);

	@Select("select count(*) from (select s.seid,s.eid,s.sid studentId,s.starttime,s.endtime,s.score,e.tid,e.sid,e.ecode from studentexam s left join exam e on e.eid=s.eid where e.tid=#{tid}) se,subject su where se.sid=su.sid")
	Long countAllExamList(String tid);

	@Select("select se.*,su.sname,st.sname stName from (select s.seid,s.eid,s.sid studentId,s.starttime,s.endtime,s.score,e.tid,e.sid,e.ecode,e.ename from studentexam s left join exam e on e.eid=s.eid where e.tid=#{tid}) se,subject su,student st where se.sid=su.sid and st.sid=se.studentId order by se.starttime limit #{startPosition},#{limit}")
	List<TeacherStuentExam> getAllExamListByPage(Integer startPosition, Integer limit, String tid);

	@Select("select q.title,aa.* from (select a.aid aid ,a.eid eid,a.qid qid,a.sid studentId,a.answer answer,a.correct correct,a.qtype qtype from answer a where a.eid=#{eid} and a.sid=#{studentId}) aa,question q where aa.qid=q.qid")
	List<StudentAnswer> getStudentAnswer(String eid, String studentId);

	@Update("update studentexam set score =#{score} where eid=#{eid} and sid=#{sid}")
	int addScore(String eid, String sid, String score);
}
