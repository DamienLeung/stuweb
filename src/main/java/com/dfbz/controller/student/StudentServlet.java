package com.dfbz.controller.student;


import com.dfbz.controller.BaseServlet;
import com.dfbz.pojo.Student;
import com.dfbz.service.StudentService;
import com.dfbz.service.impl.StudentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {

    private StudentService service = new StudentServiceImpl();

    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(1);
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameters = req.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(student);
        service.save(student);
        resp.sendRedirect("/studentTable.jsp");
    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        byte[] bytes = req.getParameter("msg").getBytes("ISO-8859-1");
        String id = new String(bytes, "UTF-8");
        service.delStudentById(Integer.parseInt(id));
        resp.sendRedirect("/studentTable.jsp");
        System.out.println(2);
    }

    public void dior(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] ids = req.getParameterValues("id");
        for (String id :
                ids) {
            System.out.print(id);
        }
        service.delAllById(ids);
        resp.sendRedirect("/studentTable.jsp");
    }

    public void addTo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(1);
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameters = req.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(student);
        service.update(student);
        resp.sendRedirect("/studentTable.jsp");
    }
}
