package com.example.examsystem.service.admin;

import com.example.examsystem.bean.*;
import com.example.examsystem.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired(required = false)
    private LoginDao loginDao;


    @Override
    public String login(LoginInfo loginInfo, HttpSession session) {

        String result = "";
        session.setAttribute("role",loginInfo.getRole());
        if ("0".equals(loginInfo.getRole())){
            LoginInfo loginInfo0 = loginDao.findAdmin(loginInfo);
            if (null == loginInfo0){
                result = "noHave";
            }else if (loginInfo.getPassword().equals(loginInfo0.getPassword())){
                result = "true";
                session.setAttribute("admin",loginInfo0);
            }else {
                result = "no";
            }

        }else if ("1".equals(loginInfo.getRole())){
            Teacher teacher = loginDao.findTeacher(loginInfo);
            if (null == teacher){
                result = "noHave";
            }else if (loginInfo.getPassword().equals(teacher.getTpassword())){
                result = "true";
                List<Subject> list = loginDao.findSubject(teacher.getTid());
                session.setAttribute("teacher",teacher);
                session.setAttribute("list",list);
            }else {
                result = "no";
            }

        }else if ("2".equals(loginInfo.getRole())){
            Student student = loginDao.findStudent(loginInfo);
            if (null == student){
                result = "noHave";
            }else if (loginInfo.getPassword().equals(student.getSpassword())){
                session.setAttribute("student",student);
                result = "true";
            }else {
                result = "no";
            }

        }

        return result;
    }

    @Override
    public String exit(HttpSession session) {
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "index";
    }

    @Override
    public ModelAndView main(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/main.html");

        String role = (String)session.getAttribute("role");

        List<Menu> list = loginDao.findRoleMenus(role);

        HashMap<String, List<Menu>> hashMap = new HashMap<>();
        hashMap.clear();

        for (Menu m : list) {
            Menu adminMenu = new Menu();
            adminMenu.setName(m.getName());
            adminMenu.setUrl(m.getUrl());
            if (hashMap.containsKey(m.getPname())) {
                hashMap.get(m.getPname()).add(adminMenu);
            } else {
                ArrayList<Menu> arrayList = new ArrayList<>();
                arrayList.add(adminMenu);
                hashMap.put(m.getPname(), arrayList);
            }
        }
        mv.addObject("menus", hashMap);

        return mv;
    }
}
