package com.example.examsystem.service.teacher;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.dto.TopicDto;

public interface TopicService
{
	Results<TopicDto> chooseTopicListByPage(Integer offset, Integer limit);
}
