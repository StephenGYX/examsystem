package com.example.examsystem.dao;

import com.example.examsystem.dto.TopicDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicDao
{

	@Select("select count(*) from question where qtype=1")
	Long countAllChooseTopic();

	@Select("select * from question q,subject s where q.sid=s.sid order by qtime desc limit #{startPosition},#{limit}")
	List<TopicDto> getAllChooseTopicByPage(Integer startPosition, Integer limit);
}
