package com.example.examsystem.dao;

import com.example.examsystem.bean.Question;
import com.example.examsystem.dto.TopicDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

	@Insert("insert into question(sid, qtype, title, qtime) values (#{sid},3,#{titles},CURDATE())")
	int blankAdd(String titles, String sid);

	int blankAddMany(List<Question> list);
}
