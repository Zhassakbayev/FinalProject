package com.epam.university_admissions.service;

import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = "No such service";
        request.setAttribute("errorMessage",errorMessage);
        request.getRequestDispatcher(Paths.ERROR_PAGE).forward(request,response);

    }
}
