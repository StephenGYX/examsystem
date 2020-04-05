package com.example.examsystem.controller.teachercontroller;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.StudentAnswer;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.bean.TeacherStuentExam;
import com.example.examsystem.dto.TopicDto;
import com.example.examsystem.service.teacher.TopicService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/topic")
public class TopicController
{
	@Autowired
	TopicService topicService;

	@ResponseBody
	@RequestMapping("/chooseList")
	public Results<TopicDto> chooseTopicList(PageTableRequest request, String sid)
	{
		request.countOffset();
		return topicService.chooseTopicListByPage(request.getOffset(), request.getLimit(), sid);
	}


	@ResponseBody
	@RequestMapping("/blankList")
	public Results<TopicDto> blankTopicList(PageTableRequest request, String sid)
	{
		request.countOffset();
		return topicService.blankTopicListByPage(request.getOffset(), request.getLimit(), sid);
	}

	@ResponseBody
	@RequestMapping("/shortList")
	public Results<TopicDto> shortTopicList(PageTableRequest request, String sid)
	{
		request.countOffset();
		return topicService.shortTopicListByPage(request.getOffset(), request.getLimit(), sid);
	}

	@ResponseBody
	@RequestMapping("/judgeList")
	public Results<TopicDto> judgeTopicList(PageTableRequest request, String sid)
	{
		request.countOffset();
		return topicService.judgeTopicListByPage(request.getOffset(), request.getLimit(), sid);
	}

	@ResponseBody
	@RequestMapping("/chooseAdd")
	public String chooseAdd(String title, String option1, String option2, String option3, String option4, String correct, String sid)
	{
		return topicService.chooseAdd(title, option1, option2, option3, option4, correct, sid);
	}

	@ResponseBody
	@RequestMapping("/blankAdd")
	public String blankAdd(String titles, String sid)
	{

		return topicService.blankAdd(titles, sid);
	}


	@ResponseBody
	@RequestMapping("/judgeAdd")
	public String judgeAdd(String title, String sid, String correct)
	{

		return topicService.judgeAdd(title, sid, correct);
	}

	@ResponseBody
	@RequestMapping("/shortAdd")
	public String shortAdd(String title, String sid)
	{

		return topicService.shortAdd(title, sid);
	}

	@RequestMapping("/bulkInsertJudge")
	@ResponseBody
	public Map<String, Object> bulkInsertJudge(MultipartFile file)
	{
		System.out.println(file);
		String str = "批量导入失败，请检查";
		String code = "500";
		boolean flag = topicService.bulkInsertJudge(file);
		if (flag)
		{
			str = "批量导入成功";
			code = "200";
		}
		Map map = new HashMap<String, Object>();
		//返回json
		map.put("msg", str);
		map.put("code", code);
		return map;
	}

	@RequestMapping("/bulkInsertBlank")
	@ResponseBody
	public Map<String, Object> bulkInsertBlank(MultipartFile file)
	{
		System.out.println(file);
		String str = "批量导入失败，请检查";
		String code = "500";
		boolean flag = topicService.bulkInsertBlank(file);
		if (flag)
		{
			str = "批量导入成功";
			code = "200";
		}
		Map map = new HashMap<String, Object>();
		//返回json
		map.put("msg", str);
		map.put("code", code);
		return map;
	}

	@RequestMapping("/bulkInsertChoose")
	@ResponseBody
	public Map<String, Object> bulkInsertChoose(MultipartFile file)
	{
		System.out.println(file);
		String str = "批量导入失败，请检查";
		String code = "500";
		boolean flag = topicService.bulkInsertChoose(file);
		if (flag)
		{
			str = "批量导入成功";
			code = "200";
		}
		Map map = new HashMap<String, Object>();
		//返回json
		map.put("msg", str);
		map.put("code", code);
		return map;
	}

	@RequestMapping("/bulkInsertShort")
	@ResponseBody
	public Map<String, Object> bulkInsertShort(MultipartFile file)
	{
		System.out.println(file);
		String str = "批量导入失败，请检查";
		String code = "500";
		boolean flag = topicService.bulkInsertShort(file);
		if (flag)
		{
			str = "批量导入成功";
			code = "200";
		}
		Map map = new HashMap<String, Object>();
		//返回json
		map.put("msg", str);
		map.put("code", code);
		return map;
	}

	@GetMapping("/downloadBlank")
	public Results download(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
	{
		//设置下载文件的名字+类型
		String filename = "填空题模板.xls";
		//设置文件路径
		File fileDirPath = new File("src/main/resources/static");
		File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
		System.out.println(file.getAbsolutePath());
		if (file.exists())
		{
			response.reset();
			// 设置强制下载不打开
			//response.setContentType("application/force-download");
			//避免中文乱码
			response.setHeader("Connection", "close");
			//设置传输的类型
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "chunked");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
			response.setContentType("application/OCTET-STREAM");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1)
				{
					os.write(buffer, 0, i);

					i = bis.read(buffer);
				}
				bis.close();
				return null;
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (bis != null)
				{
				}
				if (fis != null)
				{
					try
					{
						fis.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@GetMapping("/downloadJudge")
	public Results downloadJudge(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
	{
		//设置下载文件的名字+类型
		String filename = "判断题模板.xls";
		//设置文件路径
		File fileDirPath = new File("src/main/resources/static");
		File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
		System.out.println(file.getAbsolutePath());
		if (file.exists())
		{
			response.reset();
			// 设置强制下载不打开
			//response.setContentType("application/force-download");
			//避免中文乱码
			response.setHeader("Connection", "close");
			//设置传输的类型
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "chunked");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
			response.setContentType("application/OCTET-STREAM");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1)
				{
					os.write(buffer, 0, i);

					i = bis.read(buffer);
				}
				bis.close();
				return null;
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (bis != null)
				{
				}
				if (fis != null)
				{
					try
					{
						fis.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@GetMapping("/downloadShort")
	public Results downloadShort(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
	{
		//设置下载文件的名字+类型
		String filename = "简答题模板.xls";
		//设置文件路径
		File fileDirPath = new File("src/main/resources/static");
		File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
		System.out.println(file.getAbsolutePath());
		if (file.exists())
		{
			response.reset();
			// 设置强制下载不打开
			//response.setContentType("application/force-download");
			//避免中文乱码
			response.setHeader("Connection", "close");
			//设置传输的类型
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "chunked");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
			response.setContentType("application/OCTET-STREAM");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1)
				{
					os.write(buffer, 0, i);

					i = bis.read(buffer);
				}
				bis.close();
				return null;
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (bis != null)
				{
				}
				if (fis != null)
				{
					try
					{
						fis.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@GetMapping("/downloadChoose")
	public Results downloadChoose(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
	{
		//设置下载文件的名字+类型
		String filename = "选择题模板.xls";
		//设置文件路径
		File fileDirPath = new File("src/main/resources/static");
		File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
		System.out.println(file.getAbsolutePath());
		if (file.exists())
		{
			response.reset();
			// 设置强制下载不打开
			//response.setContentType("application/force-download");
			//避免中文乱码
			response.setHeader("Connection", "close");
			//设置传输的类型
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "chunked");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
			response.setContentType("application/OCTET-STREAM");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1)
				{
					os.write(buffer, 0, i);

					i = bis.read(buffer);
				}
				bis.close();
				return null;
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (bis != null)
				{
				}
				if (fis != null)
				{
					try
					{
						fis.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@RequestMapping("/topicUpdate")
	@ResponseBody
	public String topicUpdate(Question question)
	{
		System.out.println(question.toString());
		String msg = "error";
		int index = topicService.topicUpdate(question);
		System.out.println(index);
		if (index > 0)
		{
			msg = "success";
		}
		return msg;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String qid)
	{
		return topicService.delete(qid);
	}


	@RequestMapping("/html1")
	public String getHtml1(HttpSession httpSession)
	{
		return "teacher/choosetopicmanager";
	}

	@RequestMapping("/html2")
	public String getHtml2()
	{
		return "teacher/blanktopicmanager";
	}

	@RequestMapping("/html3")
	public String getHtml3()
	{
		return "teacher/judgetopicmanager";
	}

	@RequestMapping("/html4")
	public String getHtml4()
	{
		return "teacher/shorttopicmanager";
	}

	@RequestMapping("/html5")
	public String getHtml5()
	{
		return "teacher/testpapermanager";
	}


	@ResponseBody
	@RequestMapping("/examList")
	public Results<TeacherStuentExam> examList(PageTableRequest request, String tid)
	{
		request.countOffset();
		return topicService.examList(request.getOffset(), request.getLimit(), tid);
	}

	@ResponseBody
	@RequestMapping("/getStudentAnswer")
	public List<StudentAnswer> getStudentAnswer(String eid, String studentId)
	{
		return topicService.getStudentAnswer(eid, studentId);
	}

	@ResponseBody
	@RequestMapping("/addScore")
	public String addScore(String eid, String sid, String score)
	{
		System.out.println(score);
		return topicService.addScore(eid, sid, score);
	}

	@GetMapping("/outScore")
	@ResponseBody
	public String outScore(String tid)
	{
		String msg = "error";
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		Results<TeacherStuentExam> data = topicService.examList(0, 10000, tid);
		if (data.getDatas().size() > 0)
		{
			String[] title = {"试卷名", "姓名", "科目", "成绩"};
			File file = new File(desktopPath + "/score" + tid + ".xls");
			System.out.println(file.getAbsolutePath());
			try
			{
				file.createNewFile();
				WritableWorkbook workbook = Workbook.createWorkbook(file);
				WritableSheet sheet = workbook.createSheet("sheet1", 0);
				Label label = null;
				for (int i = 0; i < title.length; i++)
				{
					label = new Label(i, 0, title[i]);
					sheet.addCell(label);
				}
				for (int i = 1; i < data.getDatas().size() + 1; i++)
				{

					label = new Label(0, i, data.getDatas().get(i - 1).getEname());
					sheet.addCell(label);
					label = new Label(1, i, data.getDatas().get(i - 1).getStName());
					sheet.addCell(label);
					label = new Label(2, i, data.getDatas().get(i - 1).getSname());
					sheet.addCell(label);
					if (data.getDatas().get(i - 1).getScore() != null)
					{
						label = new Label(3, i, data.getDatas().get(i - 1).getScore());
					} else
					{
						label = new Label(3, i, "无成绩");
					}
					sheet.addCell(label);
				}
				workbook.write();
				workbook.close();
				msg = "success";
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return msg;
	}
}
