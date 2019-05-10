package com.diendan.svdanang.api;

import android.content.Context;

import com.diendan.svdanang.api.models.AvatarOutput;
import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.BlogPostTopicsOutput;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.api.models.ChangePasswordOutput;
import com.diendan.svdanang.api.models.EventOutput;
import com.diendan.svdanang.api.models.EventsOutput;
import com.diendan.svdanang.api.models.ForgotPasswordOutput;
import com.diendan.svdanang.api.models.LoginOutput;
import com.diendan.svdanang.api.models.PaypalDonationOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.ProjectOutput;
import com.diendan.svdanang.api.models.ProjectsOutput;
import com.diendan.svdanang.api.models.ResultDonateOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.ChangePasswordInput;
import com.diendan.svdanang.api.objects.ForgotPasswordInput;
import com.diendan.svdanang.api.objects.PaypalDonationInput;
import com.diendan.svdanang.api.objects.SignupInput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.api.objects.VolunteerInput;
import com.diendan.svdanang.models.DataDonationDetailResult;
import com.diendan.svdanang.models.EventTopic;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
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
    public static final String TASK_WS = "http://222.255.167.89/svdanang-0.0.1-SNAPSHOT/api/";//DEV api
    public static final String LOGIN_API = "auth/signin";
    public static final String SIGNUP_API = "auth/signup";
    public static final String SIGNUP_VOLUNTEER_API = "volunteers?event_id=%s";
    public static final String PROFILE_API = "users/me";
    public static final String PROJECT_API = "projects?sortBy&t&sortOrder=desc&page=%s&pageSize=%s";
    public static final String EVENT_API = "events?filter&sortBy&sortOrder=desc&page=%s&pageSize=%s";
    public static final String EVENT_BY_ID_API = "events/%s";
    public static final String BLOGPOST_TOPIC_API = "blog-topics?filter&sortBy&sortOrder=desc&page=%s&pageSize=%s";
    public static final String BLOGPOSTS_TOPIC_ID_API = "blog-posts?topicId=%s&sortBy&sortOrder&page=%s&pageSize=%s";
    public static final String BLOGPOSTS_ALL_API = "blog-posts?sortBy&sortOrder=desc&page=%s&pageSize=%s";
    public static final String BLOGPOSTS_BY_ID_API = "blog-posts/%s";
    public static final String PROJECT_BY_ID_API = "projects/%s";
    public static final String PAYPAL_DONATION_API = "paypal/make/payment";
    public static final String PAYPAL_DONATION_RESULT_API = "paypal/complete/payment/%s?paymentId=%s&token=%s&PayerID=%s";
    public static final String UPLOAD_AVATAR_API = "uploadFile";
    public static final String LOGOUT_API = "logout";
    public static final String CHANGE_PASSWORD_API = "users/me/password";
    public static final String FORGOT_PASSWORD_API = "auth/forgot";
    public static final String FORGOT_ENTER_CODE_API = "password/validateactivecode";
    public static final String RESET_PASSWORD_API = "password/reset";


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
    public ForgotPasswordOutput forgotPassword(ForgotPasswordInput forgotPasswordInput) throws ApiException, JSONException, IOException {
        JSONObject data = mHttpApi.doHttpPost(getFullUrl(FORGOT_PASSWORD_API), new Gson().toJson(forgotPasswordInput));
        ForgotPasswordOutput output = mGson.fromJson(data.toString(), ForgotPasswordOutput.class);
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
    public AvatarOutput updateAvatar(File updateFile) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpPostImages(getFullUrl(UPLOAD_AVATAR_API), new HashMap<String, String>(), updateFile);
        AvatarOutput output = (AvatarOutput) mGson.fromJson(data.toString(), AvatarOutput .class);
        return output;
    }

    public ProjectsOutput getProjects(int page,int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(PROJECT_API),page +"",5+""));
        ProjectsOutput output = (ProjectsOutput) mGson.fromJson(data.toString(), ProjectsOutput .class);
        return output;
    }
    public SignupOutput signUpVolunteer(VolunteerInput volunteerInput,Long eventId) throws ApiException, JSONException, IOException {
        JSONObject data = (JSONObject) mHttpApi.doHttpPost(String.format(getFullUrl(SIGNUP_VOLUNTEER_API),eventId +""), new Gson().toJson(volunteerInput));
        SignupOutput output = (SignupOutput) mGson.fromJson(data.toString(), SignupOutput.class);
        return output;
    }
    public EventOutput getEventById(Long id ) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(EVENT_BY_ID_API),id +""));
        EventOutput output = (EventOutput) mGson.fromJson(data.toString(), EventOutput .class);
        return output;
    }

    public EventsOutput getEvents(int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(EVENT_API),page +"",5+""));
        EventsOutput output = (EventsOutput) mGson.fromJson(data.toString(), EventsOutput .class);
        return output;
    }
    public ProjectOutput getProjectById(Long id ) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(PROJECT_BY_ID_API),id +""));
        ProjectOutput output = (ProjectOutput) mGson.fromJson(data.toString(), ProjectOutput .class);
        return output;
    }
    public BlogPostsOutput getBlogPostsByIdTopic(Long idtopic,int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(BLOGPOSTS_TOPIC_ID_API),idtopic+"",page +"",pagesize+""));
        BlogPostsOutput output = (BlogPostsOutput) mGson.fromJson(data.toString(), BlogPostsOutput .class);
        return output;
    }
    public BlogPostsOutput getBlogPosts(int page, int pagesize) throws ApiException, JSONException, IOException {
        mHttpApi.setCredentials(SharedPreferenceHelper.getInstance(this.mContext).get(Constants.EXTRAX_TOKEN_CODE));
        JSONObject data = (JSONObject) mHttpApi.doHttpGetWithHeader(String.format(getFullUrl(BLOGPOSTS_ALL_API),page +"",pagesize+""));
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

    public PaypalDonationOutput donatePaypal(PaypalDonationInput paypalDonationInput) throws ApiException, JSONException, IOException {
        JSONObject data = (JSONObject) mHttpApi.doHttpPost(getFullUrl(PAYPAL_DONATION_API), new Gson().toJson(paypalDonationInput));
        PaypalDonationOutput output = (PaypalDonationOutput) mGson.fromJson(data.toString(), PaypalDonationOutput.class);
        return output;
    }
    public ResultDonateOutput resultDonatePaypal(Long projectId, String paymentId, String token,String PayerID) throws ApiException, JSONException, IOException {
        String link = String.format(getFullUrl(PAYPAL_DONATION_RESULT_API),projectId +"",paymentId,token,PayerID);
        JSONObject data = (JSONObject) mHttpApi.doHttpPost(String.format(getFullUrl(PAYPAL_DONATION_RESULT_API),projectId +"",paymentId,token,PayerID),new Gson().toJson(null));
        ResultDonateOutput output = (ResultDonateOutput) mGson.fromJson(data.toString(), ResultDonateOutput.class);
        return output;
    }
}
