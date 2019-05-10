package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.AvatarOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;

import java.io.File;

public class UpdateAvatarTask extends BaseTask <AvatarOutput> {
    File updateFile;
    public UpdateAvatarTask(Context context, File updateFile, @Nullable ApiListener<AvatarOutput> listener) {
        super(context, listener);
        this.updateFile = updateFile;
    }

    @Override
    protected AvatarOutput callApiMethod() throws Exception {
        return mApi.updateAvatar(updateFile);
    }
}
