package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ForgotPasswordOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.ForgotPasswordInput;
import com.diendan.svdanang.api.objects.SignupInput;

public class ForgotPasswordTask extends BaseTask <ForgotPasswordOutput> {
    ForgotPasswordInput forgotPasswordInput;
    public ForgotPasswordTask(Context context, ForgotPasswordInput forgotPasswordInput, @Nullable ApiListener<ForgotPasswordOutput> listener) {
        super(context, listener);
        this.forgotPasswordInput = forgotPasswordInput ;
    }

    @Override
    protected ForgotPasswordOutput callApiMethod() throws Exception {
        return mApi.forgotPassword(forgotPasswordInput);
    }
}
