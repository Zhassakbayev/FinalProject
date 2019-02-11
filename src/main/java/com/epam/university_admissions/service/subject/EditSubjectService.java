package com.epam.university_admissions.service.subject;

import com.epam.university_admissions.dao.ConnectionPool;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;
import com.epam.university_admissions.validator.SubjectValidator;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSubjectService implements Service {
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
        String nameEn = request.getParameter(ConstantFields.SUBJECT_NAME_EN);
        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = subjectDAO.findSubjectByNameEn(nameEn);
        request.setAttribute(ConstantFields.SUBJECT_NAME_RU, subject.getNameRu());
        request.setAttribute(ConstantFields.SUBJECT_NAME_EN, subject.getNameEn());
        return Paths.FORWARD_SUBJECT_EDIT_ADMIN;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String oldNameEn = request.getParameter(ConstantFields.OLD_SUBJECT_NAME_EN);
        String newNameRu = request.getParameter(ConstantFields.SUBJECT_NAME_RU);
        String newNameEn = request.getParameter(ConstantFields.SUBJECT_NAME_EN);
        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = subjectDAO.findSubjectByNameEn(oldNameEn);
        SubjectValidator subjectValidator = new SubjectValidator();
        boolean subjectParametersValidator = subjectValidator.isViolatedSubjectParameters(newNameRu, newNameEn);
        if (!subjectParametersValidator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
            request.setAttribute(ConstantFields.VIOLATIONS, subjectValidator.getViolations());
            result = Paths.REDIRECT_SUBJECT_EDIT_ADMIN + oldNameEn;
        } else if (subjectParametersValidator) {
            subject.setNameRu(newNameRu);
            subject.setNameEn(newNameEn);
            subjectDAO.update(subject);
            result = Paths.REDIRECT_TO_SUBJECT + newNameEn;
        }
        return result;
    }
}
