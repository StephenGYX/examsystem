package com.example.examsystem.controller.teachercontroller;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.dto.TopicDto;
import com.example.examsystem.service.teacher.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/topic")
public class TopicController
{
	@Autowired
	TopicService topicService;

	@ResponseBody
	@RequestMapping("/chooseList")
	public Results<TopicDto> chooseTopicList(PageTableRequest request)
	{
		request.countOffset();
		return topicService.chooseTopicListByPage(request.getOffset(), request.getLimit());
	}

	@RequestMapping("/html")
	public String getHtml() {

		return "teacher/topicmanager";
	}

}
