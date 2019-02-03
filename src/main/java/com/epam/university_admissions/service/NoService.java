package com.epam.university_admissions.service;

import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        request.setAttribute(ConstantFields.ERROR_MESSAGE,"No such service");
        return Paths.ERROR_PAGE;
    }
}
