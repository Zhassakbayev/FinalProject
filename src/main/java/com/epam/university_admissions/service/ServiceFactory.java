package com.epam.university_admissions.service;

import com.epam.university_admissions.service.profile.LoginService;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();

    public ServiceFactory() {
        init();
    }

    private void init() {

        SERVICE_MAP.put("/login", new LoginService());
        SERVICE_MAP.put("noService", new NoService());
    }

    public Service getService(String getRequest) {
        if (getRequest== null || !SERVICE_MAP.containsKey(getRequest)) {
            return SERVICE_MAP.get("noService");
        }
        return SERVICE_MAP.get(getRequest);
    }
}
