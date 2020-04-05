package com.example.examsystem.controller.adminController;

import com.example.examsystem.base.result.PageTableRequest;
import com.example.examsystem.base.result.Results;
import com.example.examsystem.bean.Student;
import com.example.examsystem.bean.Teacher;
import com.example.examsystem.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SubService subService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @RequestMapping(value = "/main")
    public ModelAndView main(HttpSession session) {
        return loginService.main(session);
    }

    @RequestMapping(value = "/exit")
    public String exit(HttpSession session) {
        return loginService.exit(session);
    }


    /**
     * 获取科目
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSubject")
    public String subject() {
        return subService.getSubject();
    }

    /**
     * 教师表
     * @param pageTableRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/teacher")
    public Results<Teacher> teacher(PageTableRequest pageTableRequest) {
        return teacherService.teacher(pageTableRequest);
    }

    /**
     * 删除教师
     * @param teacher
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delTeacher")
    public String delTeacher(Teacher teacher) {
        return teacherService.delTeacher(teacher);
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTeacher")
    public String addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    /**
     * 学生表
     * @param pageTableRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/student")
    public Results<Student> student(PageTableRequest pageTableRequest) {
        return studentService.student(pageTableRequest);
    }

    /**
     * 获取班级
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClass")
    public String getClassList() {
        return classService.getClassAll();
    }


    /**
     * 添加学生
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStudent")
    public String addStudent(Student student) {
        return studentService.addStudent(student);
    }


    /**
     * 下载教师模板
     * @param wid
     * @param response
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/downloadTeacher")
    public Results downloadTeacher(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
    {
        //设置下载文件的名字+类型
        String filename = "教师模板.xls";
        File fileDirPath = new File("src/main/resources/static");
        File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
        System.out.println(file.getAbsolutePath());
        if (file.exists())
        {
            response.reset();
            // 设置强制下载不打开
            //response.setContentType("application/force-download");
            //避免中文乱码
            response.setHeader("Connection", "close");
            response.setHeader("Content-Transfer-Encoding", "chunked");
            //设置传输的类型
            response.setHeader("Content-Type", "application/octet-stream");

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/OCTET-STREAM");
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            FileInputStream fis = null;

            try
            {
                fis = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1)
                {
                    os.write(buffer, 0, i);

                    i = bis.read(buffer);
                }
                bis.close();
                return null;
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                if (bis != null)
                {
                }
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


    /**
     * 批量导入教师
     * @param file
     * @return
     */
    @RequestMapping("/bulkInsertTeacher")
    @ResponseBody
    public Map<String, Object> bulkInsertTeacher(MultipartFile file)
    {
        System.out.println(file);
        String code = "500";
        String str = "批量导入失败，请检查";

        boolean flag = teacherService.bulkInsertTeacher(file);
        if (flag)
        {
            code = "200";
            str = "批量导入成功";

        }
        Map map = new HashMap<String, Object>();
        //返回json
        map.put("msg", str);
        map.put("code", code);
        return map;
    }

    /**
     * 下载学生模板
     * @param wid
     * @param response
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/downloadStudent")
    public Results downloadStudent(String wid, HttpServletResponse response, HttpServletRequest request, ModelAndView model) throws IOException
    {
        //设置下载文件的名字+类型
        String filename = "学生模板.xls";
        File fileDirPath = new File("src/main/resources/static");
        File file = new File(fileDirPath.getAbsolutePath() + "/excel/" + filename);
        System.out.println(file.getAbsolutePath());
        if (file.exists())
        {
            response.reset();
            // 设置强制下载不打开
            //response.setContentType("application/force-download");
            //避免中文乱码
            response.setHeader("Connection", "close");
            response.setHeader("Content-Transfer-Encoding", "chunked");
            //设置传输的类型
            response.setHeader("Content-Type", "application/octet-stream");

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/OCTET-STREAM");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;


            try
            { OutputStream os = response.getOutputStream();
                fis = new FileInputStream(file);

                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bis.close();
                return null;
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                if (bis != null)
                {
                }
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 批量导入学生
     * @param file
     * @return
     */
    @RequestMapping("/bulkInsertStudent")
    @ResponseBody
    public Map<String, Object> bulkInsertStudent(MultipartFile file)
    {
        System.out.println(file);
        String code = "500";
        String str = "批量导入失败，请检查";

        boolean flag = studentService.bulkInsertStudent(file);
        if (flag)
        {
            code = "200";
            str = "批量导入成功";

        }
        Map map = new HashMap<String, Object>();
        //返回json
        map.put("msg", str);
        map.put("code", code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/delStudent")
    public String delStudent(Student student) {
        return studentService.delStudent(student);
    }


    @RequestMapping(value = "/html2")
    public String getStudent() {
        return "admin/student";
    }


    @RequestMapping(value = "/html4")
    public String getTeacher() {
        return "admin/teacher";
    }
}
