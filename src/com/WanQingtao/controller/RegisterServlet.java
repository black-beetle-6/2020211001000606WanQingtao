package com.WanQingtao.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private Connection con;

    @Override
    public void init() throws ServletException {
        super.init();


        con = (Connection) getServletContext().getAttribute("con");//name of attibute
        //please check the video live demo#4

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when method =post -request come in doPost

        //get all data - from Request
        String username =request.getParameter("username");//get Username <input type="text" name="username" />
        String password =request.getParameter("password");//get  password <input type="password" name="password" />
        String email =request.getParameter("email");//get <input type="text" name="email" />
        String gender =request.getParameter("gender");//get <input type="radio" name="gender"
        String birthDate =request.getParameter("birthdate");//get Birth Date <input type="text" name="birthDate" />

        //6.Insert a row into database table “usertable” in doPost()

        try {
            Statement st = con.createStatement();
            String sql = "insert into user(username,password,email,gender,birthdate)" +
                    " values('" + username + "','" + password + "','" + email + "','" + gender + "','" + birthDate + "')";
            System.out.println("sql" + sql);//check sql is ok or not

            int n = st.executeUpdate(sql);
            System.out.println("n-->" + n);//== 1 success--insert


            response.sendRedirect("login");

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
