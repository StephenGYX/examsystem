package com.example.examsystem.service.teacher;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Question;
import com.example.examsystem.dao.TopicDao;
import com.example.examsystem.dto.TopicDto;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public Results<TopicDto> blankTopicListByPage(Integer startPosition, Integer limit)
	{
		return Results.success(topicDao.countAllBlankTopic().intValue(), topicDao.getAllBlankTopicByPage(startPosition, limit));

	}

	@Override
	public Results<TopicDto> shortTopicListByPage(Integer startPosition, Integer limit)
	{
		return Results.success(topicDao.countAllShortTopic().intValue(), topicDao.getAllShortTopicByPage(startPosition, limit));

	}

	@Override
	public Results<TopicDto> judgeTopicListByPage(Integer startPosition, Integer limit)
	{
		return Results.success(topicDao.countAllJudgeTopic().intValue(), topicDao.getAllJudgeTopicByPage(startPosition, limit));
	}

	@Override
	public String blankAdd(String titles, String sid)
	{
		int index = topicDao.blankTitleQuery(titles, sid);
		String res = "error";
		if (index <= 0)
		{
			index = topicDao.blankAdd(titles, sid);
			if (index > 0)
			{
				res = "success";
			}
		}
		return res;
	}

	@Override
	public boolean bulkInsertBlank(MultipartFile file)
	{
		List<Question> list = new ArrayList<>();
		boolean flag = false;

		String fileName = file.getOriginalFilename();
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		final File excelFile;
		try
		{
			excelFile = File.createTempFile(System.currentTimeMillis() + "", prefix);
			file.transferTo(excelFile);

			Workbook workbook = Workbook.getWorkbook(excelFile);
			Sheet sheet = workbook.getSheet(0);
			//获取行
			for (int i = 0; i < sheet.getRows(); i++)
			{
				Question question = new Question();
				//获取列
				for (int j = 0; j < sheet.getColumns(); j++)
				{
					Cell cell = sheet.getCell(j, i);
					//得到单元格的内容
					if (j == 0)
					{
						question.setSid((Integer.valueOf(cell.getContents().trim())));
					} else if (j == 1)
					{
						question.setQtype(3);
						question.setTitle(cell.getContents());
						list.add(question);
					}
				}
			}
			int i = topicDao.blankAddMany(list);
			if (i > 0)
			{
				flag = true;
			}
			deleteFile(excelFile);
			workbook.close();
		} catch (IOException | BiffException e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	private void deleteFile(File... files)
	{
		for (File file : files)
		{
			if (file.exists())
			{
				file.delete();
			}
		}
	}
}
