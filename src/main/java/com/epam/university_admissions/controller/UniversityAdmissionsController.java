package com.epam.university_admissions.controller;

import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.service.ServiceFactory;
import com.epam.university_admissions.utils.ActionType;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UniversityAdmissionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp,ActionType.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp,ActionType.POST);
    }

    private void process(HttpServletRequest request , HttpServletResponse response, ActionType actionType) throws ServletException,IOException{
        String serviceName = request.getParameter(ConstantFields.SERVICE_NAME);
        ServiceFactory serviceFactory  = new ServiceFactory();
        Service service = serviceFactory.getService(serviceName);
        String result = service.execute(request,response,actionType);
        if (result == null){
            response.sendRedirect(Paths.WELCOME_PAGE);
        }else {
            if (actionType ==ActionType.GET){
                request.getRequestDispatcher(result).forward(request,response);
            }else if (actionType == ActionType.POST){
                response.sendRedirect(result);
            }
        }
    }
}
