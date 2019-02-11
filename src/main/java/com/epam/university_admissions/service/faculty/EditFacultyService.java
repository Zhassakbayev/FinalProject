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
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EditFacultyService implements Service {
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
        String result = null;
        String facultyNameEn = request.getParameter(ConstantFields.FACULTY_NAME_EN);
        FacultyDAO facultyDAO = new FacultyDAO();
        Faculty faculty = facultyDAO.findFacultyByName(facultyNameEn);

        request.setAttribute(ConstantFields.FACULTY_NAME_EN, faculty.getNameEn());
        request.setAttribute(ConstantFields.FACULTY_NAME_RU, faculty.getNameRu());
        request.setAttribute(ConstantFields.TOTAL_SEATS, faculty.getTotalSeats());
        request.setAttribute(ConstantFields.BUDGET_SEATS, faculty.getBudgetSeats());
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> otherSubjects = subjectDAO.findAllSubjectsNotFaculty(faculty);
        request.setAttribute(ConstantFields.OTHER_SUBJECTS, otherSubjects);
        List<Subject> facultySubjects = subjectDAO.findSubjectsInFaculty(faculty);
        request.setAttribute(ConstantFields.FACULTY_SUBJECTS, facultySubjects);
        return Paths.FORWARD_FACULTY_EDIT_ADMIN;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String facultyNameRu = request.getParameter(ConstantFields.FACULTY_NAME_RU);
        String facultyNameEn = request.getParameter(ConstantFields.FACULTY_NAME_EN);
        int totalSeats = Integer.valueOf(request.getParameter(ConstantFields.TOTAL_SEATS));
        int budgetSeats = Integer.valueOf(request.getParameter(ConstantFields.BUDGET_SEATS));
        String oldFacultyName = request.getParameter(ConstantFields.OLD_FACULTY_NAME);
        FacultyValidator facultyValidator = new FacultyValidator();
        boolean facultyParametersValidator = facultyValidator.isViolatedFacultyParameters(facultyNameRu, facultyNameEn, totalSeats, budgetSeats);
        if (!facultyParametersValidator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
            request.setAttribute(ConstantFields.VIOLATIONS, facultyValidator.getViolations());
        } else if (facultyParametersValidator) {
            Faculty faculty = new Faculty(facultyNameRu, facultyNameEn, totalSeats, budgetSeats);
            FacultyDAO facultyDAO = new FacultyDAO();
            Faculty oldFacultyRecord = facultyDAO.findFacultyByName(oldFacultyName);
            faculty.setId(oldFacultyRecord.getId());
            facultyDAO.update(faculty);
            String[] oldCheckedSubjects = request.getParameterValues(ConstantFields.OLD_CHECKED_SUBJECTS_ID);
            String[] newCheckedSubjectsIds = request.getParameterValues(ConstantFields.NEW_CHECKED_SUBJECTS_ID);
            FacultySubjectsDAO facultySubjectsDAO = new FacultySubjectsDAO();
            if (oldCheckedSubjects == null) {
                if (newCheckedSubjectsIds == null) {

                } else if (newCheckedSubjectsIds != null) {
                    for (String newCheckedSubjectId : newCheckedSubjectsIds) {
                        Integer subjectId = Integer.valueOf(newCheckedSubjectId);
                        FacultySubjects facultySubject = new FacultySubjects(faculty.getId(),subjectId);
                        facultySubjectsDAO.create(facultySubject);
                    }
                }
            }
            if (oldCheckedSubjects != null){
                if (newCheckedSubjectsIds == null){
                    facultySubjectsDAO.deleteAllSubjectFaculty(faculty);
                }else if (newCheckedSubjectsIds !=null){
                    Set<String> existingRecords = new HashSet<>(Arrays.asList(oldCheckedSubjects));
                    for (String newCheckedSubject : newCheckedSubjectsIds){
                        if (existingRecords.contains(newCheckedSubject)){

                        }else {
                            Integer subjectId = Integer.valueOf(newCheckedSubject);
                            FacultySubjects facultySubject = new FacultySubjects(faculty.getId(),subjectId);
                            facultySubjectsDAO.create(facultySubject);
                        }
                    }
                }
                Set<String> newRecords = new HashSet<>(Arrays.asList(newCheckedSubjectsIds));

            }
        }
        return result;
    }
}
