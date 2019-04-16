package com.diendan.svdanang.utils;

import android.widget.LinearLayout;

public class Constants {

    LinearLayout bg;
    public static final int MENU_ITEM_PROFILE = 110;
    public static final int MENU_ITEM_MANAGE_USER = 111;
    public static final int MENU_ITEM_MANAGE_MEMBER = 112;
    public static final int MENU_ITEM_MANAGE_MEETING = 113;
    public static final int MENU_ITEM_LOGOUT = 114;

    public static int WITDH = 220;
    public static String ROLE_ADMIN = "Admin";
    public static String PREF_CUSTOMER = "PREF_CUSTOMER";
    public static String PREF_USER_AVATAR = "PREF_USER_AVATAR";
    public static String NOTE_EDIT = "NOTE_EDIT";
    public static String NOTE_POST = "NOTE_POST";
    public static String NOTE_DELETE = "NOTE_DELETE";
    public static String WORK_POST = "WORK_POST";
    public static String WORK_EDIT = "WORK_EDIT";
    public static String WORK_DELETE = "WORK_DELETE";
    public static String EVENT_CREATE = "EVENT_CREATE";
    public static String EVENT_EDIT = "EVENT_EDIT";
    public static String EVENT_DELETE = "EVENT_DELETE";
    public static String CONTACT_DELETE = "CONTACT_DELETE";
    public static String PREF_USER_PROFILE = "PREF_USER_PROFILE";
    public static final String CHARSET = "UTF-8";
    public static int FAILURE_SESSION_EXPIRED = 2;
    public static String PREF_SESSION_ID = "PREF_SESSION_ID";
    public static String PREF_USER_ID = "PREF_USER_ID";
    public static String PREF_EMPLOYEES_ID = "PREF_EMPLOYEES_ID";
    public static String PREF_ROLE = "PREF_ROLE";
    public static final String PREF_PERSON_NAME = "PREF_PERSON_NAME";
    public static String PREF_GENERAL_DATA = "PREF_GENERAL_DATA";
    public static final String PREF_USERNAME = "username";
    public static final String PREF_PASSWORD_NAME = "password";
    public static final String EXTRAX_EMAIL = "email";
    public static final String EXTRAX_TOKEN_CODE = "token_code";
    public static final String EXTRAX_CONTACT_ID = "EXTRAX_CONTACT_ID";
    public static final String EXTRAX_COMPANY_ID = "EXTRAX_COMPANY_ID";
    public static final String EXTRAX_DETAIL_CONTACT = "EXTRAX_DETAIL_CONTACT";
    public static final String EXTRAX_DETAIL_COMPANY = "EXTRAX_DETAIL_COMPANY";
    public static final String COMPANY = "COMPANY";
    public static final String PERSONAL = "PERSONAL";
    public static final String CAREER = "CAREER";
    public static final String CUSTOMER_GROUP_CRM = "CUSTOMER_GROUP_CRM";
    public static final String CUSTOMER_CONTACT = "CUSTOMER_CONTACT";
    public static final String ADD = "ADD";
    public static final String EDIT_COMPANY = "EDIT_COMPANY";
    public static final String EDIT_PERSONAL = "EDIT_PERSONAL";
    public static final String ADD_PERSONAL = "ADD_PERSONAL";
    public static final String ADD_COMPANY = "ADD_COMPANY";
    public static final String ACTION_POST_WORK = "ACTION_POST_WORK";
    public static final String ACTION_POST_EVENT = "ACTION_POST_EVENT";

    //Code
    public static int CODE_SUCCESS = 0;
    public static int LIMIT_ITEMS = 10;

    public enum STATUS_WORK {
        WillAccept, Accepting, Checking, Problem, Done, Cancel
    }

    public static final String APPROVE = "APPROVE";
    public static final String UNAPPROVE = "UNAPPROVE";

}
