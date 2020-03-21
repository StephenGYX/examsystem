package com.example.examsystem.controller.adminController;


import com.example.examsystem.bean.LoginInfo;
import com.example.examsystem.service.admin.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/role")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/login")
    public String chooseTopicList(LoginInfo loginInfo, HttpSession session)
    {
       return loginService.login(loginInfo,session);
    }

}
