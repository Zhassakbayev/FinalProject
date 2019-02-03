package com.epam.university_admissions.service;

import com.epam.university_admissions.service.profile.EditProfileService;
import com.epam.university_admissions.service.profile.LoginService;
import com.epam.university_admissions.service.profile.LogoutService;
import com.epam.university_admissions.service.profile.ViewProfileService;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();

    public ServiceFactory() {
        init();
    }

    private void init() {

        SERVICE_MAP.put("login", new LoginService());
        SERVICE_MAP.put("logout",new LogoutService());
        SERVICE_MAP.put("editProfile",new EditProfileService());
        SERVICE_MAP.put("viewProfile",new ViewProfileService());
        SERVICE_MAP.put("noService", new NoService());
    }

    public Service getService(String serviceName) {
        if (serviceName== null || !SERVICE_MAP.containsKey(serviceName)) {
            return SERVICE_MAP.get("noService");
        }
        return SERVICE_MAP.get(serviceName);
    }
}
