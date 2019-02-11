package com.epam.university_admissions.service.faculty;

import com.epam.university_admissions.dao.FacultyDAO;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewAllFacultiesService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        }
        return result;
    }

    private String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        FacultyDAO facultyDAO = new FacultyDAO();
        List<Faculty> faculties = facultyDAO.findAll();
        request.setAttribute(ConstantFields.ALL_FACULTIES,faculties);
        HttpSession session = request.getSession(false);
        String role = String.valueOf(session.getAttribute(ConstantFields.ROLE));
        if (role == null || role.equals("client")){
            result = Paths.FORWARD_FACULTY_VIEW_ALL_CLIENT;
        }else if (role.equals("admin")){
            result = Paths.FORWARD_FACULTY_VIEW_ALL_ADMIN;
        }
        return result;
    }
}
