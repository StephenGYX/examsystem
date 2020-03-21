package com.example.examsystem.dao;

import com.example.examsystem.bean.Question;
import com.example.examsystem.dto.TopicDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.logging.Level;

@Mapper
public interface TopicDao
{

	@Select("select count(*) from question where qtype=1")
	Long countAllChooseTopic();

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=1 order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllChooseTopicByPage(Integer startPosition, Integer limit);

	@Select("select count(*) from question where qtype=3")
	Long countAllBlankTopic();

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=3 order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllBlankTopicByPage(Integer startPosition, Integer limit);

	@Select("select count(*) from question where qtype=4")
	Long countAllShortTopic();

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=4 order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllShortTopicByPage(Integer startPosition, Integer limit);

	@Select("select count(*) from question where qtype=2")
	Long countAllJudgeTopic();

	@Select("select * from question q,subject s where q.sid=s.sid and qtype=2 order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllJudgeTopicByPage(Integer startPosition, Integer limit);

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
}
