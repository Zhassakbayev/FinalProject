package com.epam.university_admissions.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

//    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();
    private static final Map<String,Service> SERVICE_MAP = new HashMap<>();
//    public static ServiceFactory getInstance(){
//        return SERVICE_FACTORY;
//    }

    public ServiceFactory(){
        init();
    }

    private void init(){
        SERVICE_MAP.put("/login",new LoginService());
    }

    public Service getService(String getRequest){
        return SERVICE_MAP.get(getRequest);
    }
}
