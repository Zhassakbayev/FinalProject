package com.epam.university_admissions.service.registration;

import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Role;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;
import com.epam.university_admissions.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationAdminService implements Service {
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
        return Paths.FORWARD_ADMIN_REGISTRATION_PAGE;
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
        UserValidator userValidator = new UserValidator();
        boolean validator = userValidator.isViolatedUserParameters(firstName, lastName, secondName, dateOfBirth, email, password, lang);
        if (!validator) {
            request.setAttribute(ConstantFields.ERROR_MESSAGE, "Please fill all fields!");
            request.setAttribute(ConstantFields.VIOLATIONS, userValidator.getViolations());
            result = Paths.REDIRECT_ADMIN_REGISTRATION_PAGE;
        } else if (validator) {
            User user = new User(firstName, lastName, secondName, dateOfBirth, email, password, Role.ADMIN, lang);
            UserDAO userDAO = new UserDAO();
            userDAO.create(user);
            request.setAttribute(ConstantFields.SUCCESSFUL_MESSAGE, "Your account was created!");
            result =Paths.REDIRECT_TO_PROFILE;
        }
        return result;
    }
}
