package com.example.examsystem.controller.adminController;

import com.example.examsystem.service.admin.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public ModelAndView main(HttpSession session) {
        return loginService.main(session);
    }

    @RequestMapping(value = "/exit")
    public String exit(HttpSession session){
        return loginService.exit(session);
    }

}
