package com.epam.university_admissions.utils;

import javax.swing.plaf.PanelUI;

public class Paths {

    public static final String WELCOME_PAGE = "welcome.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/views/errorPage.jsp";

    public static final String FORWARD_CLIENT_PROFILE_EDIT = "/WEB-INF/views/client/profile/edit.jsp";
    public static final String FORWARD_ADMIN_PROFILE_EDIT = "/WEB-INF/views/admin/profile/edit.jsp";

    public static final String REDIRECT_EDIT_PROFILE = "controller?service=editProfile";
    public static final String REDIRECT_TO_PROFILE = "controller?service=viewProfile";
    public static final String FORWARD_ADMIN_PROFILE = "/WEB-INF/views/admin/profile/view.jsp";
    public static final String FORWARD_CLIENT_PROFILE = "/WEB-INF/views/client/profile/view.jsp";

    public static final String FORWARD_ADMIN_REGISTRATION_PAGE = "/WEB-INF/views/admin/addAdmin.jsp";
    public static final String FORWARD_CLIENT_REGISTRATION_PAGE = "/WEB-INF/views/client/addClient.jsp";
    public static final String REDIRECT_ADMIN_REGISTRATION_PAGE = "controller?service=registration_admin";
    public static final String REDIRECT_CLIENT_REGISTRATION_PAGE = "controller?service=registration_client";

    public static final String FORWARD_ADD_FACULTY_ADMIN = "/WEB-INF/views/admin/faculty/addFaculty.jsp\"";
    public static final String REDIRECT_TO_FACULTY = "controller?service=viewFaculty&name_eng=";
    public static final String FORWARD_FACULTY_APPLY_CLIENT = "/WEB-INF/view/client/faculty/applyFaculty.jsp";
    public static final String REDIRECT_TO_VIEW_ALL_FACULTIES = "controller?service=viewAllFaculties";
    public static final String FORWARD_FACULTY_EDIT_ADMIN = "/WEB-INF/views/admin/faculty/edit.jsp";
    public static final String FORWARD_FACULTY_VIEW_ALL_CLIENT = "/WEB-INF/views/client/faculty/list.jsp";
    public static final String FORWARD_FACULTY_VIEW_ALL_ADMIN = "/WEB-INF/views/admin/faculty/list.jsp";
    public static final String FORWARD_ENTRANT_PROFILE = "/WEB-INF/views/admin/entrant/view.jsp";
    public static final String REDIRECT_ENTRANT_PROFILE = "controller?service=viewEntrant&userId=";
    public static final String FORWARD_FACULTY_VIEW_CLIENT = "/WEB-INF/view/client/faculty/view.jsp";
    public static final String FORWARD_FACULTY_VIEW_ADMIN = "/WEB-INF/views/admin/faculty/view.jsp";

    public static final String FORWARD_SUBJECT_ADD_ADMIN = "/WEB-INF/views/admin/subject/add.jsp";
    public static final String REDIRECT_TO_SUBJECT = "controller?service=viewSubject&name_eng=";
    public static final String FORWARD_SUBJECT_EDIT_ADMIN = "/WEB-INF/views/admin/subject/edit.jsp";
    public static final String REDIRECT_SUBJECT_ADD_ADMIN = "";
    public static final String REDIRECT_SUBJECT_EDIT_ADMIN = "controller?service=editSubject&name_eng=";



    public static final String REDIRECT_TO_VIEW_ALL_SUBJECTS = "controller?service=viewAllSubjects";

    public static final String FORWARD_SUBJECT_VIEW_ALL_ADMIN = "/WEB-INF/views/admin/subject/list.jsp";
    public static final String FORWARD_SUBJECT_VIEW_ADMIN = "/WEB-INF/views/admin/subject/view.jsp";
}
