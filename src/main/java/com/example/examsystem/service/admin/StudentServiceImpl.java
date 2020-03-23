package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired(required = false)
    private StudentDao studentDao;

    @Override
    public Results<Student> student(PageTableRequest pageTableRequest) {
        pageTableRequest.countOffset();
        return  Results.success(studentDao.countStudent(),studentDao.findAll(pageTableRequest.getOffset(),pageTableRequest.getLimit()));
    }

    @Override
    public String addStudent(Student student) {
        String result = "false";

        if (studentDao.findStudent(student.getSaccount()) == null){

            if (studentDao.addStudent(student) > 0){
                result = "true";
            }

        }else {
            result = "have";
        }

        return result;
    }

    @Override
    public String delStudent(Student student) {
        String result = "false";
        if (studentDao.findExam(student.getSid()) > 0){
            result = "have";
        }else {
            if (studentDao.delStudent(student.getSaccount()) > 0){
                result = "true";
            }
        }

        return result;
    }


}
