package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Teacher;

public interface TeacherService {
    String addTeacher(Teacher teacher);

    Results<Teacher> teacher(PageTableRequest pageTableRequest);

    String delTeacher(Teacher teacher);
}
