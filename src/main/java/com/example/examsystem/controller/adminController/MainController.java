package com.example.examsystem.controller.adminController;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.service.admin.LoginService;
import com.example.examsystem.service.admin.SubService;
import com.example.examsystem.service.admin.TeacherService;
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

    @ResponseBody
    @RequestMapping(value = "/delTeacher")
    public String delTeacher(Teacher teacher) {
        return teacherService.delTeacher(teacher);
    }

    @ResponseBody
    @RequestMapping(value = "/addTeacher")
    public String addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping(value = "/html1")
    public String getClass1() {
        return "admin/class";
    }

    @RequestMapping(value = "/html2")
    public String getStudent() {
        return "admin/student";
    }

    @RequestMapping(value = "/html3")
    public String getSubject() {
        return "admin/subject";
    }

    @RequestMapping(value = "/html4")
    public String getTeacher() {
        return "admin/teacher";
    }
}
