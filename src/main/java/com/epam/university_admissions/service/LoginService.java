package com.epam.university_admissions.service;

import com.epam.university_admissions.dao.Dao;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmailPassword(email,password);
        if (user==null){
            request.setAttribute("errorMessage","You are not registered!");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",user.getEmail());
            session.setAttribute("userRole",user.getRole());
            session.setAttribute("lang",user.getLanguage());
            request.setAttribute("name",user.getFirstName());
            request.setAttribute("email",user.getEmail());
        }
        request.getRequestDispatcher("/WEB-INF/views/userinfo.jsp").forward(request,response);

    }
}
