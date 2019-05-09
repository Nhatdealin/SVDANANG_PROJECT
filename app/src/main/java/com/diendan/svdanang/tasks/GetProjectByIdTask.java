package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.ProjectOutput;

public class GetProjectByIdTask extends BaseTask <ProjectOutput> {
    Long id;
    public GetProjectByIdTask(Context context, Long id, @Nullable ApiListener<ProjectOutput> listener) {
        super(context, listener);
        this.id = id;
    }

    @Override
    protected ProjectOutput callApiMethod() throws Exception {
        return mApi.getProjectById(id);
    }
}
