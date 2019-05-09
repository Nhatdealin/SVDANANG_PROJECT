package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.SignupInput;
import com.diendan.svdanang.api.objects.VolunteerInput;

public class SignupVolunteerTask extends BaseTask <SignupOutput> {
    VolunteerInput volunteerInput;
    Long eventId;
    public SignupVolunteerTask(Context context, VolunteerInput volunteerInput,Long eventId, @Nullable ApiListener<SignupOutput> listener) {
        super(context, listener);
        this.volunteerInput = volunteerInput ;
        this.eventId = eventId;

    }

    @Override
    protected SignupOutput callApiMethod() throws Exception {
        return mApi.signUpVolunteer(volunteerInput,eventId);
    }
}
