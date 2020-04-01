package com.example.examsystem.dao;

import com.example.examsystem.bean.ClassInfo;
import com.example.examsystem.bean.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubDao {

    @Select("select * from subject ")
    List<Subject> getSubject();

    @Select("select * from class")
    List<ClassInfo> getClassInfo();
}
