package com.epam.university_admissions.service.profile;

import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.dao.UserDAO;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userEmail = String.valueOf(session.getAttribute("email"));
        String role = String.valueOf(session.getAttribute("userRole"));

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(userEmail);

        request.setAttribute(ConstantFields.FIRST_NAME,user.getFirstName());
        request.setAttribute(ConstantFields.LAST_NAME,user.getLastName());
        request.setAttribute(ConstantFields.SECOND_NAME,user.getSecondName());
        request.setAttribute(ConstantFields.DATE_OF_BIRTH,user.getDateOfBirth());
        request.setAttribute(ConstantFields.EMAIL,user.getEmail());
        request.setAttribute(ConstantFields.PASSWORD,user.getPassword());
        request.setAttribute(ConstantFields.LANG,user.getLanguage());

        if (role.equalsIgnoreCase("client")){
            EntrantDAO entrantDAO = new EntrantDAO();
            Entrant entrant = entrantDAO.findEntrantByUserId(user);
            request.setAttribute(ConstantFields.IIN,entrant.getIin());
            request.setAttribute(ConstantFields.CITY,entrant.getCity());
            request.setAttribute(ConstantFields.DISTRICT,entrant.getDistrict());
            request.setAttribute(ConstantFields.SCHOOL_NAME,entrant.getSchoolName());
            request.setAttribute(ConstantFields.IS_BLOCKED,entrant.getBlockedStatus());
            request.getRequestDispatcher(Paths.CLIENT_PROFILE_EDIT).forward(request,response);
        }else if (role.equalsIgnoreCase("admin")){
            request.getRequestDispatcher(Paths.ADMIN_PROFILE_EDIT).forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request ,HttpServletResponse response)throws ServletException, IOException{
        String email = request.getParameter(ConstantFields.EMAIL);
        String firstName = request.getParameter(ConstantFields.FIRST_NAME);
        String lastName = request.getParameter(ConstantFields.LAST_NAME);
        String secondName = request.getParameter(ConstantFields.SECOND_NAME);
        String dateOfBirth = request.getParameter(ConstantFields.DATE_OF_BIRTH);
        String password = request.getParameter(ConstantFields.PASSWORD);
        String lang = request.getParameter(ConstantFields.LANG);

        HttpSession session = request.getSession(false);
        String role = String.valueOf(session.getAttribute(ConstantFields.ROLE));



    }
}
