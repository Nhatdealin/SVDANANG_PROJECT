package com.diendan.svdanang.api;

import android.content.Context;

import com.diendan.svdanang.api.models.LoginOutput;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.diendan.svdanang.api.exception.ApiException;
import com.diendan.svdanang.api.models.BaseOutput;
import com.diendan.svdanang.api.objects.LoginInput;
import com.diendan.svdanang.http.HttpApiWithSessionAuth;

/**
 * Created by dcmen on 08/31/16.
 */
public class TaskApi {
    // URL
    public static final String TASK_WS = "https://api-svdanang.herokuapp.com/api/";//DEV api
    public static final String LOGIN_API = "auth/signin";
    public static final String PROFILE_API = "user/me";
    public static final String GET_NOTE = "crm/notes/%s";
    public static final String GET_LIST_NOTE = "crm/notes/filter/%s/%s?searchTerm=%s";
    public static final String GET_LIST_WORK = "crm/works/filter/%s/%s?searchTerm=%s";
    public static final String POST_NOTE = "crm/notes/create";
    public static final String DELETE_NOTE = "crm/notes/%s/delete";
    public static final String EDIT_NOTE = "crm/notes/edit";
    public static final String EDIT_COMPANY = "crm/contacts/company/edit";
    public static final String ADD_COMPANY = "crm/contacts/company/create";
    public static final String GET_LIST_EVENT = "crm/events/filter/%s/%s?searchTerm=%s";
    public static final String EDIT_EVENT = "crm/events/edit";
    public static final String DELETE_EVENT = "crm/events/%s/delete";
    public static final String CREATE_EVENT = "crm/events/create";
    public static final String EDIT_WORK = "crm/works/edit";
    public static final String DELETE_WORK = "crm/works/%s/delete";
    public static final String POST_WORK = "crm/works/create";
    public static final String GET_ACTIVITY_STATUS = "data/activitystatus";
    public static final String GET_SHARE_HOLDER_TYPE = "data/shareholdertypes";
    public static final String EDIT_PERSONAL = "crm/contacts/invidual/edit";
    public static final String ADD_PERSONAL = "crm/contacts/invidual/create";
    public static final String GET_ACTIVITY_INVOLVE = "data/crm/getrelatedobjects";
    public static final String GET_ACTIVITY_EVENT_TYPE = "data/activityeventtype";
    public static final String GET_LIST_CONTACT = "crm/contacts/invidual/filter/%s/%s?searchTerm=%s&potentialSource=%s";
    public static final String GET_DETAIL_CONTACT = "crm/contacts/invidual/%s";
    public static final String DELETE_CONTACT = "crm/contacts/invidual/%s/delete";
    public static final String GET_LIST_CAREER = "data/prospectcustomerbusinesses";
    public static final String GET_LIST_COMPANY = "crm/contacts/company/filter/%s/%s?searchTerm=%s&classify=%s";
    public static final String GET_DETAIL_COMPANY = "crm/contacts/company/%s";
    public static final String GET_CUSTOMER_GROUP_CRM = "data/customergroupcrms";
    public static final String GET_CUSTOMER_TYPES = "data/customertypes";
    public static final String UPDATE_AVATAR_API = "/user/me/updateavatar";
    public static final String GET_HBC_ROLES = "data/hbcroles";
    public static final String GET_TITLE_NAME = "data/titlenames";
    public static final String GET_POTENTIAL_LIST = "data/prospectcustomersources";
    public static final String EDIT_PROFILE_API = "employee/requesteditemployee";
    public static final String UPLOAD_AVATAR_API = "user/me/updateavatar";
    public static final String LOGOUT_API = "logout";
    public static final String CHANGE_PASSWORD_API = "user/password/change";
    public static final String FORGOT_PASSWORD_API = "password/forgot";
    public static final String FORGOT_ENTER_CODE_API = "password/validateactivecode";
    public static final String RESET_PASSWORD_API = "password/reset";
    public static final String GET_CUSTOMERS_API = "customers/search/%s/%s";
    public static final String GET_CUSTOMERS_CONTACT = "customercontact/%s/%s?searchTerm=%s";
    public static final String GET_EMPLOYYES_API = "employee/getall";
    public static final String GET_GENERAL_DATA_API = "data/customertype";
    public static final String ADD_CUSTOMER_API = "customers/create";
    public static final String EDIT_CUSTOMER_API = "customers/edit";
    public static final String DELETE_CUSTOMER_API = "customers/delete";
    public static final String GET_DETAIL_CUSTOMER_API = "customers/%s";


    private static final Logger LOG = Logger
            .getLogger(TaskApi.class.getCanonicalName());
    private HttpApiWithSessionAuth mHttpApi;
    private String mDomain;
    private Context mContext;
    private Gson mGson;

    public TaskApi(Context context) {
        mContext = context;
        mHttpApi = new HttpApiWithSessionAuth(context);
        mGson = new Gson();
        mDomain = TASK_WS;
    }


    public TaskApi setCredentials(String token) {
        if (token == null || token.length() == 0)
            mHttpApi.clearCredentials();
        else
            mHttpApi.setCredentials(token);
        return this;
    }


    public String getFullUrl(String subUrl) {
        return mDomain + subUrl;
    }

    public LoginOutput loginByEmail(LoginInput input) throws ApiException, JSONException, IOException {
        JSONObject data = mHttpApi.doHttpPost(getFullUrl(LOGIN_API), new Gson().toJson(input));
        LoginOutput output = mGson.fromJson(data.toString(), LoginOutput.class);
        return output;
    }

    public BaseOutput logout() throws ApiException, JSONException, IOException {
        JSONObject data = mHttpApi.doHttpPost(getFullUrl(LOGOUT_API), "");
        BaseOutput output = mGson.fromJson(data.toString(), BaseOutput.class);
        return output;
    }

    public LoginOutput loginUsername(LoginInput loginInput) throws ApiException, JSONException, IOException {
        JSONObject data = mHttpApi.doHttpPost(getFullUrl(LOGIN_API), new Gson().toJson(loginInput));
        LoginOutput output = mGson.fromJson(data.toString(), LoginOutput.class);
        return output;
    }
}
