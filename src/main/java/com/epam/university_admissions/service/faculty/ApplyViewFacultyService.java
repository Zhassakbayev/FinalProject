package com.epam.university_admissions.service.faculty;

import com.epam.university_admissions.dao.*;
import com.epam.university_admissions.entity.*;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ApplyViewFacultyService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        } else if (actionType == ActionType.POST) {
            result = doPost(request, response);
        }
        return result;
    }

    private String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String facultyNameEn = request.getParameter(ConstantFields.FACULTY_NAME_EN);
        FacultyDAO facultyDAO = new FacultyDAO();
        Faculty faculty = facultyDAO.findFacultyByName(facultyNameEn);
        request.setAttribute(ConstantFields.FACULTY_ID, faculty.getId());
        request.setAttribute(ConstantFields.FACULTY_NAME_RU,faculty.getNameRu());
        request.setAttribute(ConstantFields.FACULTY_NAME_EN,faculty.getNameEn());
        request.setAttribute(ConstantFields.TOTAL_SEATS,faculty.getTotalSeats());
        request.setAttribute(ConstantFields.BUDGET_SEATS,faculty.getBudgetSeats());

        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> facultySubjects = subjectDAO.findSubjectsInFaculty(faculty);
        request.setAttribute(ConstantFields.FACULTY_SUBJECTS,facultySubjects);
        Collection<Subject> allSubjects = subjectDAO.findAll();
        request.setAttribute(ConstantFields.ALL_SUBJECTS,allSubjects);
        return Paths.FORWARD_FACULTY_APPLY_CLIENT;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String email = String.valueOf(session.getAttribute(ConstantFields.EMAIL));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(email);

        EntrantDAO entrantDAO = new EntrantDAO();
        Entrant entrant = entrantDAO.findEntrantByUserId(user);
        FacultyEntrantsDAO facultyEntrantsDAO = new FacultyEntrantsDAO();
        int facultyId = Integer.valueOf(request.getParameter(ConstantFields.FACULTY_ID));
        FacultyEntrants facultyEntrants = new FacultyEntrants(facultyId,entrant.getId());
        FacultyEntrants existingRecord = facultyEntrantsDAO.find(facultyEntrants.getId());
        if (existingRecord!=null){
            return Paths.REDIRECT_TO_VIEW_ALL_FACULTIES;
        }else {
            Map<String,String[]> parameterMap = request.getParameterMap();
            MarkDAO markDAO = new MarkDAO();
            for (String parameterName : parameterMap.keySet()){
//                if ()
            }
        }
        return  "don't finished";
    }
}
