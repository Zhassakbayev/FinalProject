package com.epam.university_admissions.service.profile;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Entrant;
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

public class ViewProfileService implements Service {
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
        HttpSession session = request.getSession(false);
        String email = String.valueOf(session.getAttribute(ConstantFields.EMAIL));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(email);
        request.setAttribute(ConstantFields.FIRST_NAME, user.getFirstName());
        request.setAttribute(ConstantFields.LAST_NAME, user.getLastName());
        request.setAttribute(ConstantFields.SECOND_NAME, user.getSecondName());
        request.setAttribute(ConstantFields.DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(ConstantFields.EMAIL, user.getEmail());
        request.setAttribute(ConstantFields.ROLE, user.getRole());
        String role = user.getRole();
        if (role.equals("admin")) {
            result = Paths.FORWARD_ADMIN_PROFILE;
        } else if (role.equals("client")) {
            EntrantDAO entrantDAO = new EntrantDAO();
            Entrant entrant = entrantDAO.findEntrantByUserId(user);
            request.setAttribute(ConstantFields.IIN, entrant.getIin());
            request.setAttribute(ConstantFields.CITY, entrant.getCity());
            request.setAttribute(ConstantFields.DISTRICT, entrant.getDistrict());
            request.setAttribute(ConstantFields.SCHOOL_NAME, entrant.getSchoolName());
            request.setAttribute(ConstantFields.IS_BLOCKED, entrant.getBlockedStatus());
            result = Paths.FORWARD_CLIENT_PROFILE ;
        }
        return result;
    }
}
