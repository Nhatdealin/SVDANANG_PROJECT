package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.exception.ApiException;
import com.diendan.svdanang.api.models.LoginOutput;
import com.diendan.svdanang.api.objects.LoginInput;

import org.json.JSONException;

import java.io.IOException;

public class LoginTask  extends BaseTask <LoginOutput> {
    LoginInput login;
    public LoginTask(Context context, LoginInput login, @Nullable ApiListener<LoginOutput> listener) {
        super(context, listener);
        this.login = login;
    }

    @Override
    protected LoginOutput callApiMethod() throws Exception {
        return mApi.loginUsername(login);
    }
}
