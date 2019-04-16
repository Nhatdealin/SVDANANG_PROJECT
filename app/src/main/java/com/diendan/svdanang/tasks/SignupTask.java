package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.SignupOutput;

import com.diendan.svdanang.api.objects.SignupInput;

public class SignupTask extends BaseTask <SignupOutput> {
    SignupInput signup;
    public SignupTask(Context context, SignupInput signup, @Nullable ApiListener<SignupOutput> listener) {
        super(context, listener);
        this.signup = signup ;
    }

    @Override
    protected SignupOutput callApiMethod() throws Exception {
        return mApi.signUpAccount(signup);
    }
}
