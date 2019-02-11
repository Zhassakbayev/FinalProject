package com.epam.university_admissions.service.registration;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Role;
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
import java.io.IOException;

public class RegistrationClientService implements Service {
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
        return Paths.FORWARD_CLIENT_REGISTRATION_PAGE;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = null;
        String firstName = request.getParameter(ConstantFields.FIRST_NAME);
        String lastName = request.getParameter(ConstantFields.LAST_NAME);
        String secondName = request.getParameter(ConstantFields.SECOND_NAME);
        String dateOfBirth = request.getParameter(ConstantFields.DATE_OF_BIRTH);
        String email = request.getParameter(ConstantFields.EMAIL);
        String password = request.getParameter(ConstantFields.PASSWORD);
        String lang = request.getParameter(ConstantFields.LANG);

        String iin = request.getParameter(ConstantFields.IIN);
        String city = request.getParameter(ConstantFields.CITY);
        String district = request.getParameter(ConstantFields.DISTRICT);
        String schoolName = request.getParameter(ConstantFields.SCHOOL_NAME);
        UserValidator userValidator = new UserValidator();
        boolean userParametersValidator = userValidator.isViolatedUserParameters(firstName, lastName, secondName, dateOfBirth, email, password, lang);
        if (userParametersValidator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields!");
            request.setAttribute(ConstantFields.VIOLATIONS, userValidator.getViolations());
            result = Paths.REDIRECT_CLIENT_REGISTRATION_PAGE;
        } else if (!userParametersValidator) {
            EntrantValidator entrantValidator = new EntrantValidator();
            boolean entrantParametersValidator = entrantValidator.isViolatedEntrantParameters(iin, city, district, schoolName);
            if (entrantParametersValidator) {
                request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields!");
                request.setAttribute(ConstantFields.VIOLATIONS, entrantValidator.getViolations());
            }else if (!entrantParametersValidator){
                User user = new User(firstName, lastName, secondName, dateOfBirth, email, password, Role.CLIENT, lang);
                UserDAO userDAO = new UserDAO();
                userDAO.create(user);
                Entrant entrant = new Entrant(iin,city,district,schoolName);
                EntrantDAO entrantDAO = new EntrantDAO();
                entrantDAO.create(entrant);
                request.setAttribute(ConstantFields.SUCCESSFUL_MESSAGE,"Your account was created! Welcome our education system");
                result = Paths.WELCOME_PAGE;
            }
        }
        return result;
    }
}
