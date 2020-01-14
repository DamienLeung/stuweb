package com.dfbz.controller.user;

import com.dfbz.controller.BaseServlet;
import com.dfbz.pojo.User;
import com.dfbz.service.UserService;
import com.dfbz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class LoginServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String psw = req.getParameter("psw");
        System.out.println(username);
        System.out.println(psw);
        String path = req.getContextPath();
        boolean b = userService.validateUser(new User(username, psw));
        String msg;
        if (b) {
            resp.setCharacterEncoding("UTF-8");
            resp.sendRedirect(path + "/studentTable.jsp");

        } else {
            msg = "登錄失敗";
            resp.setCharacterEncoding("UTF-8");
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect(path + "/error.jsp");
        }
//        resp.setCharacterEncoding("UTF-8");
//        resp.sendRedirect(path + "/loginView?msg=" + msg);

    }
}
