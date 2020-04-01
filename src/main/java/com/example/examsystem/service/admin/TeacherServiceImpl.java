package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.LoginInfo;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.dao.LoginDao;
import com.example.examsystem.dao.SubDao;
import com.example.examsystem.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired(required = false)
    private TeacherDao teacherDao;

    @Autowired(required = false)
    private LoginDao loginDao;


    @Override
    public String addTeacher(Teacher teacher) {

        String result = "false";
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccount(teacher.getTaccount());

        if (loginDao.findTeacher(loginInfo) == null){
            if (teacherDao.addTeacher(teacher) > 0){
                Teacher teacher1 = loginDao.findTeacher(loginInfo);
                for (int i = 0; i < teacher.getTsubject().length; i++) {
                   teacherDao.addTeacherSubject(teacher1.getTid(),teacher.getTsubject()[i]);
                }
                for (int i = 0; i < teacher.getTclass().length; i++) {
                    teacherDao.addTclass(teacher1.getTid(),teacher.getTclass()[i]);
                }
                result = "true";
            }
        }else {
            result = "have";
        }


        return result;
    }

    @Override
    public Results<Teacher> teacher(PageTableRequest pageTableRequest) {

        pageTableRequest.countOffset();
        return  Results.success(teacherDao.countTeacher(),teacherDao.findAll(pageTableRequest.getOffset(),pageTableRequest.getLimit()));
    }

    @Override
    public String delTeacher(Teacher teacher) {
        String result = "false";

        if (teacherDao.findExam(teacher.getTid()) > 0){
            System.out.println("222");
            result = "have";
        }else {
            if (teacherDao.delSubject(teacher.getTid()) > 0 && teacherDao.delTeacher(teacher.getTid()) > 0 && teacherDao.delClass(teacher.getTid()) > 0){
             result = "true";
            }
        }

        return result;
    }
}
