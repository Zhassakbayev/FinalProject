package com.epam.university_admissions.service.profile;

import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return Paths.WELCOME_PAGE;
    }
}
