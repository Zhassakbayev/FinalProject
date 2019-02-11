package com.epam.university_admissions.service.subject;

import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewSubjectService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        }
        return result;
    }

    private String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectNameEn = request.getParameter(ConstantFields.SUBJECT_NAME_EN);
        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = subjectDAO.findSubjectByNameEn(subjectNameEn);
        request.setAttribute(ConstantFields.SUBJECT_ID,subject.getId());
        request.setAttribute(ConstantFields.SUBJECT_NAME_RU,subject.getNameRu());
        request.setAttribute(ConstantFields.SUBJECT_NAME_EN,subject.getNameEn());
        return Paths.FORWARD_SUBJECT_VIEW_ADMIN;
    }
}
