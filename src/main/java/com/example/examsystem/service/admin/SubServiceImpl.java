package com.example.examsystem.service.admin;

import com.example.examsystem.bean.Subject;
import com.example.examsystem.dao.SubDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubServiceImpl implements SubService {

    @Autowired(required = false)
    private SubDao subDao;

    @Override
    public String getSubject() {
        List<Subject> list = subDao.getSubject();

        return new Gson().toJson(list);
    }
}
