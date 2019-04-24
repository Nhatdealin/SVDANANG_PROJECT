package com.diendan.svdanang.api;

import android.content.Context;

import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.BlogPostTopicsOutput;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.api.models.ChangePasswordOutput;
import com.diendan.svdanang.api.models.EventsOutput;
import com.diendan.svdanang.api.models.LoginOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.ProjectsOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.ChangePasswordInput;
import com.diendan.svdanang.api.objects.SignupInput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    public static final String TASK_WS = "https://svdanang.herokuapp.com/api/";//DEV api
    public static final String LOGIN_API = "auth/signin";
    public static final String SIGNUP_API = "auth/signup";
    public static final String PROFILE_API = "users/me";
    public static final String PROJECT_API = "projects?sortBy=createdAt&sortOrder&page=%s&pageSize=%s";
    public static final String EVENT_API = "events?filter&sortBy&sortOrder&page=%s&pageSize=%s";
    public static final String BLOGPOST_TOPIC_API = "blog-topics?filter&sortBy&sortOrder&page=%s&pageSize=%s";
    public static final String BLOGPOSTS_TOPIC_ID_API = "blog-posts/?topic_id=%s&sortBy&sortOrder&page=%s&pageSize=%s";
    public static final String BLOGPOSTS_BY_ID_API = "blog-posts/%s";
    public static final String EMAIL_AVAILABILITY = "users/checkEmailAvailability?email=%s";
    public static final String USERNAME_AVAILABILITY = "users/checkUsernameAvailability";
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
    public static final String CHANGE_PASSWORD_API = "users/me/password";
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
    public SignupOutput signUpAccount(SignupInput signupInput) throws ApiException, JSONException, IOException {
        JSONObject data = (JSONObject) mHttpApi.doHttpPost(getFullUrl(SIGNUP_API), new Gson().toJson(signupInput));
        SignupOutput output = (SignupOutput) mGson.fromJson(data.toString(), SignupOutput.class);
        return output;
    }
    public ChangePasswordOutput changePassword(ChangePasswordInput changePasswordInput) throws ApiException, JSONException, IOException {
        JSONObject data = (JSONObject) mHttpApi.doHttpPut(getFullUrl(CHANGE_PASSWORD_API), new Gson().toJson(changePasswordInput));
        ChangePasswordOutput output = (ChangePasswordOutput) mGson.fromJson(data.toString(), ChangePasswordOutput.class);
        return output;
    }
    public ProfileOutput getProfile() throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(PROFILE_API)));
        ProfileOutput output = mGson.fromJson(data.toString(), ProfileOutput .class);
        return output;
    }
    public ProfileOutput updateProfile(UpdateProfileInput updateProfileInput) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpPut(getFullUrl(PROFILE_API), new Gson().toJson(updateProfileInput));
        ProfileOutput output = (ProfileOutput) mGson.fromJson(data.toString(), ProfileOutput .class);
        return output;
    }
    public ProjectsOutput getProjects(int page,int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(PROJECT_API),page +"",5+""));
        ProjectsOutput output = (ProjectsOutput) mGson.fromJson(data.toString(), ProjectsOutput .class);
        return output;
    }
    public EventsOutput getEvents(int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(EVENT_API),page +"",5+""));
        EventsOutput output = (EventsOutput) mGson.fromJson(data.toString(), EventsOutput .class);
        return output;
    }
    public BlogPostsOutput getBlogPostsByIdTopic(Long idtopic,int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(BLOGPOSTS_TOPIC_ID_API),idtopic+"",page +"",pagesize+""));
        BlogPostsOutput output = (BlogPostsOutput) mGson.fromJson(data.toString(), BlogPostsOutput .class);
        return output;
    }
    public BlogPostTopicsOutput getBlogPostsTopics(int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(BLOGPOST_TOPIC_API),page +"",pagesize+""));
        BlogPostTopicsOutput output = (BlogPostTopicsOutput) mGson.fromJson(data.toString(), BlogPostTopicsOutput .class);
        return output;
    }
    public BlogPostOutput getBlogPostById(Long id ) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(BLOGPOSTS_BY_ID_API),id +""));
        BlogPostOutput output = (BlogPostOutput) mGson.fromJson(data.toString(), BlogPostOutput .class);
        return output;
    }
}
