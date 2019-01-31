package com.epam.university_admissions.service.profile;

import com.epam.university_admissions.dao.Dao;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter(ConstantFields.EMAIL);
        String password = request.getParameter(ConstantFields.PASSWORD);
        UserDAO userDAO = new UserDAO();

        User user = userDAO.findUserByEmailPassword(userEmail,password);
        if (user == null){
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Can't find user with such login/password");
        }else{
            HttpSession session = request.getSession(true);
            session.setAttribute(ConstantFields.EMAIL,user.getEmail());
            session.setAttribute(ConstantFields.ROLE,user.getRole());
            session.setAttribute(ConstantFields.LANG,user.getLanguage());
        }
        response.sendRedirect(Paths.VIEW_ALL_FACULTIES);
    }
}
