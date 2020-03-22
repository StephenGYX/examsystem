package com.example.examsystem.dao;

import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherDao {

    @Insert("insert into teacher(taccount,tpassword,tsex,tname,tbirth,teducation,tregtime) " +
            "values(#{taccount},#{tpassword},#{tsex},#{tname},#{tbirth},#{teducation},#{tregtime})")
    int addTeacher(Teacher teacher);

    @Insert("insert into teachersubject(tid,sid) values(#{tid},#{s})")
    int addTeacherSubject(String tid, String s);

    @Select("select count(*) from teacher")
    int countTeacher();

    @Select("select * from teacher limit #{offset},#{limit}")
    List<Teacher> findAll(Integer offset, Integer limit);

    @Select("select count(*) from exam where tid = #{tid}")
    int findExam(String tid);

    @Delete("delete from teacher where tid = #{tid}")
    int delTeacher(String tid);

    @Delete("delete from teachersubject where tid = #{tid}")
    int delSubject(String tid);
}
