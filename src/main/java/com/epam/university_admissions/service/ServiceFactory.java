package com.epam.university_admissions.service;

import com.epam.university_admissions.service.faculty.*;
import com.epam.university_admissions.service.profile.EditProfileService;
import com.epam.university_admissions.service.profile.LoginService;
import com.epam.university_admissions.service.profile.LogoutService;
import com.epam.university_admissions.service.profile.ViewProfileService;
import com.epam.university_admissions.service.registration.RegistrationAdminService;
import com.epam.university_admissions.service.registration.RegistrationClientService;
import com.epam.university_admissions.service.subject.*;

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
        SERVICE_MAP.put("edit_profile",new EditProfileService());
        SERVICE_MAP.put("view_profile",new ViewProfileService());
        SERVICE_MAP.put("no_service", new NoService());
        SERVICE_MAP.put("registration_admin", new RegistrationAdminService());
        SERVICE_MAP.put("registration_client", new RegistrationClientService());
        SERVICE_MAP.put("add_faculty",new AddFacultyService());
        SERVICE_MAP.put("delete_faculty",new DeleteFacultyService());
//        SERVICE_MAP.put("edit_faculty", new EditFacultyService());
//        SERVICE_MAP.put("apply_faculty", new ApplyViewFacultyService());
        SERVICE_MAP.put("view_all_faculties", new ViewAllFacultiesService());
        SERVICE_MAP.put("view_faculty",new ViewFacultyService());
        SERVICE_MAP.put("view_entrant", new ViewEntrantService());
        SERVICE_MAP.put("add_subject",new AddSubjectService());
        SERVICE_MAP.put("delete_subject", new DeleteSubjectService());
        SERVICE_MAP.put("edit_subject",new EditSubjectService());
        SERVICE_MAP.put("view_all_subjects", new ViewAllSubjectsService());
        SERVICE_MAP.put("view_subject", new ViewSubjectService());

    }

    public Service getService(String serviceName) {
        if (serviceName== null || !SERVICE_MAP.containsKey(serviceName)) {
            return SERVICE_MAP.get("noService");
        }
        return SERVICE_MAP.get(serviceName);
    }
}
