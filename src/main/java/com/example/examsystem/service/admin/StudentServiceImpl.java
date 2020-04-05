package com.example.examsystem.service.admin;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.dao.StudentDao;
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
public class StudentServiceImpl implements StudentService {


    @Autowired(required = false)
    private StudentDao studentDao;
    @Override
    public Results<Student> student(PageTableRequest pageTableRequest) {
        pageTableRequest.countOffset();
        return  Results.success(studentDao.countStudent(),studentDao.findAll(pageTableRequest.getOffset(),pageTableRequest.getLimit()));
    }

    @Override
    public String addStudent(Student student) {
        String result = "false";

        if (studentDao.findStudent(student.getSaccount()) == null){

            if (studentDao.addStudent(student) > 0){
                result = "true";
            }

        }else {
            result = "have";
        }

        return result;
    }

    @Override
    public String delStudent(Student student) {
        String result = "false";
        if (studentDao.findExam(student.getSid()) > 0){
            result = "have";
        }else {
            if (studentDao.delStudent(student.getSaccount()) > 0){
                result = "true";
            }
        }

        return result;
    }

    @Override
    public boolean bulkInsertStudent(MultipartFile file) {
        List<Student> list = new ArrayList<>();
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
                Student student = new Student();
                //获取列
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    //得到单元格的内容
                    if (j == 0) {
                        student.setSaccount((cell.getContents()));
                    } else if (j == 1) {
                        student.setSpassword(cell.getContents());
                    } else if (j == 2) {
                        student.setSname(cell.getContents());
                    } else if (j == 3) {
                        student.setSclass(cell.getContents());
                    } else if (j == 4) {
                        student.setSsex(cell.getContents());
                    } else if (j == 5) {
                        student.setSbirth(cell.getContents());
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        student.setSregtime(s.format(new Date()));
                        list.add(student);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                studentDao.addStudent(list.get(i));
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
