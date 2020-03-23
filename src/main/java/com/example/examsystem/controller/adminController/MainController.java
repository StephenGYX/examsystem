package com.example.examsystem.controller.adminController;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SubService subService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @RequestMapping(value = "/main")
    public ModelAndView main(HttpSession session) {
        return loginService.main(session);
    }

    @RequestMapping(value = "/exit")
    public String exit(HttpSession session) {
        return loginService.exit(session);
    }


    /**
     * 获取科目
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSubject")
    public String subject() {
        return subService.getSubject();
    }

    /**
     * 教师表
     * @param pageTableRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/teacher")
    public Results<Teacher> teacher(PageTableRequest pageTableRequest) {
        return teacherService.teacher(pageTableRequest);
    }

    /**
     * 删除教师
     * @param teacher
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delTeacher")
    public String delTeacher(Teacher teacher) {
        return teacherService.delTeacher(teacher);
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTeacher")
    public String addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    /**
     * 学生表
     * @param pageTableRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/student")
    public Results<Student> student(PageTableRequest pageTableRequest) {
        return studentService.student(pageTableRequest);
    }

    /**
     * 获取班级
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClass")
    public String getClassList() {
        return classService.getClassAll();
    }


    /**
     * 添加学生
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStudent")
    public String addStudent(Student student) {
        return studentService.addStudent(student);
    }


    @ResponseBody
    @RequestMapping(value = "/delStudent")
    public String delStudent(Student student) {
        return studentService.delStudent(student);
    }


    @RequestMapping(value = "/html2")
    public String getStudent() {
        return "admin/student";
    }


    @RequestMapping(value = "/html4")
    public String getTeacher() {
        return "admin/teacher";
    }
}
