package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.LoginInfo;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.dao.LoginDao;
import com.example.examsystem.dao.TeacherDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired(required = false)
    private TeacherDao teacherDao;

    @Autowired(required = false)
    private LoginDao loginDao;


    @Override
    public String addTeacher(Teacher teacher) {

        String result = "false";
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccount(teacher.getTaccount());

        if (loginDao.findTeacher(loginInfo) == null) {
            if (teacherDao.addTeacher(teacher) > 0) {
                Teacher teacher1 = loginDao.findTeacher(loginInfo);
                for (int i = 0; i < teacher.getTsubject().length; i++) {
                    teacherDao.addTeacherSubject(teacher1.getTid(), teacher.getTsubject()[i]);
                }
                for (int i = 0; i < teacher.getTclass().length; i++) {
                    teacherDao.addTclass(teacher1.getTid(), teacher.getTclass()[i]);
                }
                result = "true";
            }
        } else {
            result = "have";
        }


        return result;
    }

    @Override
    public Results<Teacher> teacher(PageTableRequest pageTableRequest) {

        pageTableRequest.countOffset();
        return Results.success(teacherDao.countTeacher(), teacherDao.findAll(pageTableRequest.getOffset(), pageTableRequest.getLimit()));
    }

    @Override
    public String delTeacher(Teacher teacher) {
        String result = "false";

        if (teacherDao.findExam(teacher.getTid()) > 0) {
            System.out.println("222");
            result = "have";
        } else {
            if (teacherDao.delSubject(teacher.getTid()) > 0 && teacherDao.delTeacher(teacher.getTid()) > 0 && teacherDao.delClass(teacher.getTid()) > 0) {
                result = "true";
            }
        }

        return result;
    }

    @Override
    public boolean bulkInsertTeacher(MultipartFile file) {
        List<Teacher> list = new ArrayList<>();
        boolean flag = false;

        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        final File excelFile;
        try {
            excelFile = File.createTempFile(System.currentTimeMillis() + "", prefix);
            file.transferTo(excelFile);

            Workbook workbook = Workbook.getWorkbook(excelFile);
            Sheet sheet = workbook.getSheet(0);
            //获取行
            for (int i = 0; i < sheet.getRows(); i++) {
                Teacher teacher = new Teacher();
                //获取列
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    //得到单元格的内容
                    if (j == 0) {
                        teacher.setTaccount((cell.getContents()));
                    } else if (j == 1) {
                        teacher.setTpassword(cell.getContents());
                    } else if (j == 2) {
                        teacher.setTname(cell.getContents());
                    } else if (j == 3) {
                        teacher.setTsex(cell.getContents());
                    } else if (j == 4) {
                        teacher.setTeducation(cell.getContents());
                    } else if (j == 5) {
                        teacher.setTbirth(cell.getContents());
                    } else if (j == 6) {

                        teacher.setTclass(cell.getContents().split("，"));
                    } else if (j == 7) {
                        teacher.setTsubject(cell.getContents().split("，"));
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        teacher.setTregtime(s.format(new Date()));
                        list.add(teacher);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                teacherDao.addTeacher(list.get(i));
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setAccount(list.get(i).getTaccount());
                Teacher teacher = loginDao.findTeacher(loginInfo);
                for (int j = 0; j < list.get(i).getTclass().length; j++) {
                    teacherDao.addTclass(teacher.getTid(),list.get(i).getTclass()[j]);
                }
                for (int j = 0; j < list.get(i).getTsubject().length; j++) {
                    teacherDao.addTeacherSubject(teacher.getTid(),list.get(i).getTsubject()[j]);
                }
                flag = true;
            }

            deleteFile(excelFile);
            workbook.close();
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
