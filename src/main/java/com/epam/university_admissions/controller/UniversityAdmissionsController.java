package com.epam.university_admissions.controller;

import com.epam.university_admissions.service.Service;
import com.epam.university_admissions.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UniversityAdmissionsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    private void process(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
        String requestURI = request.getRequestURI();
        String requestTest = "/login";
        ServiceFactory serviceFactory  = new ServiceFactory();
        Service service = serviceFactory.getService(requestTest);
        service.execute(request,response);
    }
}
