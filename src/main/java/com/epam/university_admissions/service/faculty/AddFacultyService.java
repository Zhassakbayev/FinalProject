package com.epam.university_admissions.service.faculty;

import com.epam.university_admissions.dao.FacultyDAO;
import com.epam.university_admissions.dao.FacultySubjectsDAO;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.FacultySubjects;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;
import com.epam.university_admissions.validator.FacultyValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AddFacultyService implements Service {
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
        SubjectDAO subjectDAO = new SubjectDAO();
        Collection<Subject> allSubjects = subjectDAO.findAll();
        request.setAttribute(ConstantFields.ALL_SUBJECTS, allSubjects);
        return Paths.FORWARD_ADD_FACULTY_ADMIN;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String facultyNameRu = request.getParameter(ConstantFields.FACULTY_NAME_RU);
        String facultyNameEn = request.getParameter(ConstantFields.FACULTY_NAME_EN);
        int totalSeats = Integer.valueOf(request.getParameter(ConstantFields.TOTAL_SEATS));
        int budgetSeats = Integer.valueOf(request.getParameter(ConstantFields.BUDGET_SEATS));
        FacultyValidator facultyValidator = new FacultyValidator();
        boolean validator = facultyValidator.isViolatedFacultyParameters(facultyNameRu, facultyNameEn, totalSeats, budgetSeats);
        if (!validator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
        } else if (validator) {
            Faculty faculty = new Faculty(facultyNameRu, facultyNameEn, totalSeats, budgetSeats);
            FacultyDAO facultyDAO = new FacultyDAO();
            facultyDAO.create(faculty);
            String[] facultySubjects = request.getParameterValues(ConstantFields.ALL_SUBJECTS);
            if (facultySubjects != null) {
                FacultySubjectsDAO facultySubjectsDAO = new FacultySubjectsDAO();
                for (String subject : facultySubjects) {
                    FacultySubjects facultySubject = new FacultySubjects(Integer.valueOf(subject), faculty.getId());
                    facultySubjectsDAO.create(facultySubject);
                }
            }
            result = Paths.REDIRECT_TO_FACULTY + facultyNameEn;
        }
        return result;
    }
}
