package com.gbarwinski.organizerspring.utility;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class UrlPaths {

    public static final String HOME = "/home";
    public static final String CSS_ALLOW_ALL = "/css/**";
    public static final String JS_ALLOW_ALL = "/js/**";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGIN_ALLOW_ALL = "/login**";
    public static final String LOGIN_ERROR = "/login-error";
    public static final String LOGOUT = "/logout";
    public static final String LOGOUT_SUCCESS = "/login?logout=true";
    public static final String TASK_INFORMATION = "/taskInformation";
    public static final String PROJECTS = "/projects";
}
