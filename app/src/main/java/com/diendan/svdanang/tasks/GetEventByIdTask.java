package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.EventOutput;
import com.diendan.svdanang.api.models.ProjectOutput;

public class GetEventByIdTask extends BaseTask <EventOutput> {
    Long id;
    public GetEventByIdTask(Context context, Long id, @Nullable ApiListener<EventOutput> listener) {
        super(context, listener);
        this.id = id;
    }

    @Override
    protected EventOutput callApiMethod() throws Exception {
        return mApi.getEventById(id);
    }
}
