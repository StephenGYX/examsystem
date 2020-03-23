package com.example.examsystem.dao;

import com.example.examsystem.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDao {

    @Select("select count(*) from student")
    int countStudent();

    @Select("select * from student limit #{offset},#{limit}")
    List<Student> findAll(Integer offset, Integer limit);

    @Select("select * from student where saccount = #{saccount}")
    Student findStudent(String saccount);

    @Insert("insert into student(saccount,spassword,sname,ssex,sbirth,sclass,sregtime) values " +
            "(#{saccount},#{spassword},#{sname},#{ssex},#{sbirth},#{sclass},#{sregtime})")
    int addStudent(Student student);

    @Select("select count(*) from studentexam where sid = #{#sid}")
    int findExam(String sid);

    @Delete("delete from student where saccount = #{saccount}")
    int delStudent(String saccount);
}
