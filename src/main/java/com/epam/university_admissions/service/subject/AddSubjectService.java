package com.epam.university_admissions.service.subject;

import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;
import com.epam.university_admissions.validator.SubjectValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddSubjectService implements Service {
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
        return Paths.FORWARD_SUBJECT_ADD_ADMIN;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String nameRu = request.getParameter(ConstantFields.SUBJECT_NAME_RU);
        String nameEn = request.getParameter(ConstantFields.SUBJECT_NAME_EN);
        SubjectValidator subjectValidator = new SubjectValidator();
        boolean subjectParametersValidator = subjectValidator.isViolatedSubjectParameters(nameRu, nameEn);
        if (!subjectParametersValidator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
            request.setAttribute(ConstantFields.VIOLATIONS, subjectValidator.getViolations());
            result = Paths.REDIRECT_SUBJECT_ADD_ADMIN;
        } else if (subjectParametersValidator) {
            SubjectDAO subjectDAO = new SubjectDAO();
            Subject subject = new Subject(nameRu, nameEn);
            subjectDAO.create(subject);
            result = Paths.REDIRECT_TO_SUBJECT + nameEn;
        }
        return result;
    }
}
