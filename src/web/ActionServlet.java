package web;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * Created by EYlee on 7/28/15.
 */
@WebServlet(name = "ActionServlet")
public class ActionServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        if (action.equals("/login")) {
            //先比较验证码
            //number1:用户输入
            String number1 = request.getParameter("number");
            //2 session 对象上绑定的验证码
            HttpSession session = request.getSession();
            String number2= (String)session.getAttribute("number");
            if(!number1.equalsIgnoreCase(number2)){
                //验证码不匹配，提示用户
                request.setAttribute("number_error","验证码错误");
                request.getRequestDispatcher("login.jsp").forward(request,response);
                return;

            }
            //先读取用户名 密码
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            //访问数据库，查看数据库中有没有匹配的记录
            UserDAO dao = new UserDAO();
            try {
                User user = dao.findByUsername(username);
                if (user != null && user.getPwd().equals(pwd)) {
                    //登录成功，跳转到主功能页面
//                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    response.sendRedirect("main.jsp");
                } else {
                    //登录失败，跳转到登录页面提示用户
                    request.setAttribute("login_error", "Username or 密码 error");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //让容器来处理系统异常
                throw new ServletException(e);
            }
        }
    }


}
