package com.example.examsystem.controller.studentcontroller;

import com.example.examsystem.bean.Question;
import com.example.examsystem.bean.Student;
import com.example.examsystem.service.student.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 这是考试页面的控制层
 */

@Controller
@RequestMapping("/studentexam")
public class StudentExamController
{
	@Autowired
	private StudentExamService studentExamServiceImpl;
	/**
	 * 跳转考试页面
	 * @return
	 */
	@RequestMapping("/exammain")
	public String exammain(){
		return "student/studentexam";
	}

	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/tologin")
	public String tologin(){
		return "index";
	}

	/**
	 * 判断用户是否存在
	 * @param session
	 * @return
	 */
	@GetMapping("/getSid")
	@ResponseBody
	public Student getSid(HttpSession session){
		Student student=(Student) session.getAttribute("student");
		if (student == null)
		{
			student=new Student();
			student.setSsex("error");
		}else
		{
			String res=studentExamServiceImpl.getOnline(session);
			if (res.equals("success"))
			{
				student.setSsex("getOnline");
			}
		}
		return student;
	}


	/**
	 * 用户进行退出登录
	 * @param session
	 * @return
	 */
	@GetMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
	        session.removeAttribute(em.nextElement().toString());
        }
        return "success";
	}

	@PostMapping("/examnum")
	@ResponseBody
	public List<Question> examQuestion(String examnum,HttpSession session){
		return studentExamServiceImpl.examQuestion(examnum,session);
	}

	/**
	 * 考试提交
	 * @return
	 */
	@PostMapping("/doexam")
	@ResponseBody
	public String doexam(HttpServletRequest request){
		return studentExamServiceImpl.doexam(request);
	}
}
