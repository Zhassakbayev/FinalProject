package com.epam.university_admissions.service.faculty;

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
import java.io.IOException;
import java.sql.Connection;

public class ViewEntrantService implements Service {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException {
        String result = null;
        if (ActionType.GET == actionType) {
            result = doGet(request, response);
        } else if (ActionType.POST == actionType) {
            result = doPost(request, response);
        }
        return result;
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.valueOf(request.getParameter(ConstantFields.USER_ID));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.find(userId);
        request.setAttribute(ConstantFields.FIRST_NAME, user.getFirstName());
        request.setAttribute(ConstantFields.LAST_NAME, user.getLastName());
        request.setAttribute(ConstantFields.SECOND_NAME, user.getSecondName());
        request.setAttribute(ConstantFields.DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(ConstantFields.EMAIL, user.getEmail());
        request.setAttribute(ConstantFields.ROLE, user.getRole());
        EntrantDAO entrantDAO = new EntrantDAO();
        Entrant entrant = entrantDAO.findEntrantByUserId(user);
        request.setAttribute(ConstantFields.ENTRANT_ID,entrant.getId());
        request.setAttribute(ConstantFields.IIN, entrant.getIin());
        request.setAttribute(ConstantFields.CITY, entrant.getCity());
        request.setAttribute(ConstantFields.DISTRICT, entrant.getDistrict());
        request.setAttribute(ConstantFields.SCHOOL_NAME, entrant.getSchoolName());
        request.setAttribute(ConstantFields.IS_BLOCKED, entrant.getBlockedStatus());
        return Paths.FORWARD_ENTRANT_PROFILE;
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer entrantId = Integer.valueOf(request.getParameter(ConstantFields.ENTRANT_ID));
        EntrantDAO entrantDAO = new EntrantDAO();
        Entrant entrant =entrantDAO.find(entrantId);
        boolean updatedBlockedStatus = !entrant.getBlockedStatus();
        entrant.setBlockedStatus(updatedBlockedStatus);
        entrantDAO.update(entrant);
        return  Paths.REDIRECT_ENTRANT_PROFILE + entrant.getId();
    }
}
