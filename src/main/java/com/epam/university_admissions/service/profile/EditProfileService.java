package com.epam.university_admissions.service.profile;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;
import com.epam.university_admissions.validator.EntrantValidator;
import com.epam.university_admissions.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileService implements Service {
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
        String result = null;
        HttpSession session = request.getSession(false);
        String userEmail = String.valueOf(session.getAttribute(ConstantFields.EMAIL));
        String role = String.valueOf(session.getAttribute(ConstantFields.ROLE));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(userEmail);

        request.setAttribute(ConstantFields.FIRST_NAME, user.getFirstName());
        request.setAttribute(ConstantFields.LAST_NAME, user.getLastName());
        request.setAttribute(ConstantFields.SECOND_NAME, user.getSecondName());
        request.setAttribute(ConstantFields.DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(ConstantFields.EMAIL, user.getEmail());
        request.setAttribute(ConstantFields.PASSWORD, user.getPassword());
        request.setAttribute(ConstantFields.LANG, user.getLanguage());

        if (role.equalsIgnoreCase("client")) {
            EntrantDAO entrantDAO = new EntrantDAO();
            Entrant entrant = entrantDAO.findEntrantByUserId(user);
            request.setAttribute(ConstantFields.IIN, entrant.getIin());
            request.setAttribute(ConstantFields.CITY, entrant.getCity());
            request.setAttribute(ConstantFields.DISTRICT, entrant.getDistrict());
            request.setAttribute(ConstantFields.SCHOOL_NAME, entrant.getSchoolName());
            request.setAttribute(ConstantFields.IS_BLOCKED, entrant.getBlockedStatus());
            result = Paths.FORWARD_CLIENT_PROFILE_EDIT;
        } else if (role.equalsIgnoreCase("admin")) {
            result = Paths.FORWARD_ADMIN_PROFILE_EDIT;
        }
        return result;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String oldEmail = request.getParameter(ConstantFields.OLD_EMAIL);
        String email = request.getParameter(ConstantFields.EMAIL);
        String firstName = request.getParameter(ConstantFields.FIRST_NAME);
        String lastName = request.getParameter(ConstantFields.LAST_NAME);
        String secondName = request.getParameter(ConstantFields.SECOND_NAME);
        String dateOfBirth = request.getParameter(ConstantFields.DATE_OF_BIRTH);
        String password = request.getParameter(ConstantFields.PASSWORD);
        String lang = request.getParameter(ConstantFields.LANG);

        HttpSession session = request.getSession(false);
        String role = String.valueOf(session.getAttribute(ConstantFields.ROLE));

        UserValidator userValidator = new UserValidator();
        boolean userParametersValidator = userValidator.isViolatedUserParameters(firstName, lastName, secondName, dateOfBirth, email, password, lang);
        UserDAO userDAO = new UserDAO();
        if (!userParametersValidator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
            request.setAttribute(ConstantFields.VIOLATIONS, userValidator.getViolations());
            result = Paths.REDIRECT_EDIT_PROFILE;
        } else if (userParametersValidator) {
            if (role.equals("admin")) {
                User user = userDAO.findUserByEmail(oldEmail);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setSecondName(secondName);
                user.setDateOfBirth(dateOfBirth);
                user.setEmail(email);
                user.setPassword(password);
                user.setLanguage(lang);
                userDAO.update(user);
                session.setAttribute(ConstantFields.EMAIL, email);
                session.setAttribute(ConstantFields.LANG, lang);
                result = Paths.REDIRECT_TO_PROFILE;
            } else if (role.equals("client")) {
                String iin = request.getParameter(ConstantFields.IIN);
                String city = request.getParameter(ConstantFields.CITY);
                String district = request.getParameter(ConstantFields.DISTRICT);
                String schoolName = request.getParameter(ConstantFields.SCHOOL_NAME);
                boolean blockedStatus = Boolean.parseBoolean(request.getParameter(ConstantFields.IS_BLOCKED));
                EntrantValidator entrantValidator = new EntrantValidator();
                boolean entrantParametersValidator = entrantValidator.isViolatedEntrantParameters(iin, city, district, schoolName);
                if (!entrantParametersValidator) {
                    request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields properly!");
                    request.setAttribute(ConstantFields.VIOLATIONS, entrantValidator.getViolations());
                    result = Paths.REDIRECT_EDIT_PROFILE;
                } else if (entrantParametersValidator) {
                    User user = userDAO.findUserByEmail(oldEmail);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setSecondName(secondName);
                    user.setDateOfBirth(dateOfBirth);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setLanguage(lang);
                    userDAO.update(user);

                    EntrantDAO entrantDAO = new EntrantDAO();
                    Entrant entrant = entrantDAO.findEntrantByUserId(user);
                    entrant.setIin(iin);
                    entrant.setCity(city);
                    entrant.setDistrict(district);
                    entrant.setSchoolName(schoolName);
                    entrant.setBlockedStatus(blockedStatus);
                    entrantDAO.update(entrant);
                    session.setAttribute(ConstantFields.EMAIL, email);
                    session.setAttribute(ConstantFields.LANG, lang);
                    result = Paths.REDIRECT_TO_PROFILE;
                }
            }
        }
        return result;
    }
}
