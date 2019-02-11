package com.epam.university_admissions.service.faculty;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.FacultyDAO;
import com.epam.university_admissions.dao.SubjectDAO;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.entity.User;
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
import java.util.Map;
import java.util.TreeMap;

public class ViewFacultyService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        }
        return result;
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String facultyNameEn = request.getParameter(ConstantFields.FACULTY_NAME_EN);
        FacultyDAO facultyDAO = new FacultyDAO();
        Faculty facultyRecord = facultyDAO.findFacultyByName(facultyNameEn);
        request.setAttribute(ConstantFields.FACULTY_ID, facultyRecord.getId());
        request.setAttribute(ConstantFields.FACULTY_NAME_RU, facultyRecord.getNameRu());
        request.setAttribute(ConstantFields.FACULTY_NAME_EN, facultyRecord.getNameEn());
        request.setAttribute(ConstantFields.TOTAL_SEATS, facultyRecord.getTotalSeats());
        request.setAttribute(ConstantFields.BUDGET_SEATS, facultyRecord.getBudgetSeats());
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> facutlySubjects = subjectDAO.findSubjectsInFaculty(facultyRecord);
        request.setAttribute(ConstantFields.FACULTY_SUBJECTS, facutlySubjects);
        HttpSession session = request.getSession(false);
        String role = String.valueOf(session.getAttribute(ConstantFields.ROLE));
        if (role == null || role.equals("client")) {
            result = Paths.FORWARD_FACULTY_VIEW_CLIENT;
        } else if (role.equals("admin")) {
            EntrantDAO entrantDAO = new EntrantDAO();
            List<Entrant> entrants = entrantDAO.findAllEntrantInFaculty(facultyRecord);
            Map<Entrant, String> facultyEntrants = new TreeMap<>();
            UserDAO userDAO = new UserDAO();
            for (Entrant entrant : entrants) {
                User user = userDAO.find(entrant.getId());
                facultyEntrants.put(entrant, user.getFirstName() + " " + user.getLastName() + " " + user.getSecondName());

            }
            request.setAttribute(ConstantFields.FACULTY_ENTRANTS,facultyEntrants);
            result = Paths.FORWARD_FACULTY_VIEW_ADMIN;
        }
        return result;
    }
}
