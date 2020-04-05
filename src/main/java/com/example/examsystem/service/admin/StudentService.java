package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.bean.Teacher;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Results<Student> student(PageTableRequest pageTableRequest);

    String addStudent(Student student);

    String delStudent(Student student);

    boolean bulkInsertStudent(MultipartFile file);
}
