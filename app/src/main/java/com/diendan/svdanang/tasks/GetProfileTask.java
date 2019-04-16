package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.SignupInput;

public class GetProfileTask extends BaseTask <ProfileOutput> {

    public GetProfileTask(Context context, @Nullable ApiListener<ProfileOutput> listener) {
        super(context, listener);
    }

    @Override
    protected ProfileOutput callApiMethod() throws Exception {
        return mApi.getProfile();
    }
}
