package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ChangePasswordOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.ChangePasswordInput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;

public class ChangePasswordTask extends BaseTask <ChangePasswordOutput> {
    ChangePasswordInput changePasswordInput;
    public ChangePasswordTask(Context context, ChangePasswordInput changePasswordInput, @Nullable ApiListener<ChangePasswordOutput> listener) {
        super(context, listener);
        this.changePasswordInput = changePasswordInput;
    }

    @Override
    protected ChangePasswordOutput callApiMethod() throws Exception {
        return mApi.changePassword(changePasswordInput);
    }
}
