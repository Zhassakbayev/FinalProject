package com.epam.university_admissions.service.subject;

import com.epam.university_admissions.dao.FacultySubjectsDAO;
import com.epam.university_admissions.dao.MarkDAO;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.FacultySubjects;
import com.epam.university_admissions.entity.Mark;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class DeleteSubjectService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.GET) {
            result = doPost(request, response);
        }
        return result;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        int subjectId = Integer.valueOf(request.getParameter(ConstantFields.SUBJECT_ID));
        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = subjectDAO.find(subjectId);
        FacultySubjectsDAO facultySubjectsDAO = new FacultySubjectsDAO();
        List<FacultySubjects> facultySubjectsList = facultySubjectsDAO.findAll();
        MarkDAO markDAO = new MarkDAO();
        List<Mark> markList = markDAO.findAll();
        int presenceSubjectInFaculties = 0;
        for (FacultySubjects facultySubject : facultySubjectsList) {
            if (facultySubject.getSubjectId() == subjectId) {
                presenceSubjectInFaculties++;
            }
        }
        int presenceSubjectInMarkTable = 0;
        for (Mark mark : markList) {
            if (mark.getSubjectId() == subjectId) {
                presenceSubjectInMarkTable++;
            }
        }
        if (presenceSubjectInFaculties == 0) {
            if (presenceSubjectInMarkTable == 0) {
                subjectDAO.delete(subject);
                result = Paths.REDIRECT_TO_VIEW_ALL_SUBJECTS;
            } else {
                result = Paths.REDIRECT_TO_SUBJECT;
            }
        } else {
            result = Paths.REDIRECT_TO_SUBJECT + subject.getNameEn();
        }
        return  result;
    }
}
