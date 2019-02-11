package com.epam.university_admissions.service.faculty;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.FacultyDAO;
import com.epam.university_admissions.dao.FacultySubjectsDAO;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Faculty;
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

public class DeleteFacultyService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.POST) {
            result = doPost(request, response);
        }
        return result;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facultyId = Integer.valueOf(request.getParameter(ConstantFields.FACULTY_ID));
        FacultyDAO facultyDAO = new FacultyDAO();
        Faculty faculty = facultyDAO.find(facultyId);
        EntrantDAO entrantDAO = new EntrantDAO();
        List<Entrant> facultyEntrants = entrantDAO.findAllEntrantInFaculty(faculty);
        if (facultyEntrants != null){
            request.setAttribute(ConstantFields.ERROR_MESSAGE,"There are records in other tables that rely on this faculty.");
            return Paths.REDIRECT_TO_FACULTY+faculty.getNameEn();
        }else {
            FacultySubjectsDAO facultySubjectsDAO = new FacultySubjectsDAO();
            facultySubjectsDAO.deleteAllSubjectFaculty(faculty);
            facultyDAO.delete(faculty);
            return Paths.REDIRECT_TO_VIEW_ALL_FACULTIES;
        }
    }
}
