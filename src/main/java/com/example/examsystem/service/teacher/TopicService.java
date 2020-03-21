package com.example.examsystem.service.teacher;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.dto.TopicDto;
import org.springframework.web.multipart.MultipartFile;

public interface TopicService
{
	Results<TopicDto> chooseTopicListByPage(Integer offset, Integer limit);

	Results<TopicDto> blankTopicListByPage(Integer offset, Integer limit);

	Results<TopicDto> shortTopicListByPage(Integer offset, Integer limit);

	Results<TopicDto> judgeTopicListByPage(Integer offset, Integer limit);

	String blankAdd(String titles,String sid);

	boolean bulkInsertBlank(MultipartFile file);
}
