package com.epam.university_admissions.service;

import com.epam.university_admissions.utils.ActionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Service {

   String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws ServletException, IOException;
}
