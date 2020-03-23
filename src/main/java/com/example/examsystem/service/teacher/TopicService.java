package com.example.examsystem.service.teacher;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Question;
import com.example.examsystem.dto.TopicDto;
import org.springframework.web.multipart.MultipartFile;

public interface TopicService
{
	Results<TopicDto> chooseTopicListByPage(Integer offset, Integer limit, String sid);

	Results<TopicDto> blankTopicListByPage(Integer offset, Integer limit, String sid);

	Results<TopicDto> shortTopicListByPage(Integer offset, Integer limit, String sid);

	Results<TopicDto> judgeTopicListByPage(Integer offset, Integer limit, String sid);

	String blankAdd(String titles, String sid);

	boolean bulkInsertBlank(MultipartFile file);

	String chooseAdd(String title, String option1, String option2, String option3, String option4, String correct, String sid);

	boolean bulkInsertChoose(MultipartFile file);

	String judgeAdd(String title, String sid, String correct);

	boolean bulkInsertJudge(MultipartFile file);

	String shortAdd(String title, String sid);

	boolean bulkInsertShort(MultipartFile file);

	int topicUpdate(Question question);

	String delete(String qid);
}
