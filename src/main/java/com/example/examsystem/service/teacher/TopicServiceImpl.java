package com.example.examsystem.service.teacher;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.dao.TopicDao;
import com.example.examsystem.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService
{

	@Autowired
	TopicDao topicDao;

	@Override
	public Results<TopicDto> chooseTopicListByPage(Integer startPosition, Integer limit)
	{
		return Results.success(topicDao.countAllChooseTopic().intValue(), topicDao.getAllChooseTopicByPage(startPosition, limit));
	}
}
