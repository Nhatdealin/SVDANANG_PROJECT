package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;

public class UpdateProfileTask extends BaseTask <ProfileOutput> {
    UpdateProfileInput update;
    public UpdateProfileTask(Context context, UpdateProfileInput updateprofile,@Nullable ApiListener<ProfileOutput> listener) {
        super(context, listener);
        this.update = updateprofile;
    }

    @Override
    protected ProfileOutput callApiMethod() throws Exception {
        return mApi.updateProfile(update);
    }
}
