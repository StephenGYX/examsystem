package com.example.examsystem.service.admin;

import com.example.examsystem.bean.LoginInfo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface LoginService {
    String login(LoginInfo loginInfo, HttpSession session);

    String exit(HttpSession session);

    ModelAndView main(HttpSession session);
}
