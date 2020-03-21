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
	public String chooseAdd(String title, String option1, String option2, String option3, String option4, String correct, String sid)
	{

		int index = topicDao.chooseTitleQuery(title, sid);
		String res = "error";
		if (index <= 0)
		{
			index = topicDao.chooseAdd(title, option1, option2, option3, option4, correct, sid);
			if (index > 0)
			{
				res = "success";
			}
		}
		return res;
	}

	@Override
	public String judgeAdd(String title, String sid, String correct)
	{
		String res = "error";
		int index = topicDao.judgeAdd(title, correct, sid);
		if (index > 0)
		{
			res = "success";
		}
		return res;
	}

	@Override
	public String shortAdd(String title, String sid)
	{
		String res = "error";
		int index = topicDao.shortAdd(title, sid);
		if (index > 0)
		{
			res = "success";
		}
		return res;
	}

	@Override
	public boolean bulkInsertShort(MultipartFile file)
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
						question.setQtype(4);
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

	@Override
	public int topicUpdate(Question question)
	{
		return topicDao.topicUpdate(question);
	}

	@Override
	public boolean bulkInsertJudge(MultipartFile file)
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
						question.setQtype(2);
						question.setTitle(cell.getContents());
					} else if (j == 2)
					{
						question.setCorrect(cell.getContents());
						list.add(question);
					}
				}
			}
			int i = topicDao.judgeAddMany(list);
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


	@Override
	public boolean bulkInsertChoose(MultipartFile file)
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
						question.setQtype(1);
						question.setTitle(cell.getContents());
						list.add(question);
					} else if (j == 2)
					{
						question.setOption1(cell.getContents());
					} else if (j == 3)
					{
						question.setOption2(cell.getContents());
					} else if (j == 4)
					{
						question.setOption3(cell.getContents());
					} else if (j == 5)
					{
						question.setOption4(cell.getContents());
					} else if (j == 6)
					{
						question.setCorrect(cell.getContents());
						list.add(question);
					}
				}
			}
			int i = topicDao.chooseAddMany(list);
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
