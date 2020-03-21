package com.example.examsystem.dao;

import com.example.examsystem.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginDao {

    @Select("select maccount account,mpassword password from manager where maccount = #{account}")
    LoginInfo findAdmin(LoginInfo loginInfo);

    @Select("select * from teacher where taccount = #{account}")
    Teacher findTeacher(LoginInfo loginInfo);

    @Select("select * from student where saccount = #{account}")
    Student findStudent(LoginInfo loginInfo);

    @Select("select b.sid sid,b.sname sname from teachersubject a ,subject b where a.sid = b.sid " +
            "and a.tid = #{tid}")
    List<Subject> findSubject(String tid);

    @Select("select a.name pname,b.name name,b.url url from menu a,menu b where a.mid = b.pid and" +
            " a.role = #{role} and b.role = #{role}")
    List<Menu> findRoleMenus(String role);
}
