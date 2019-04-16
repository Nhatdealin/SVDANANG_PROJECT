package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.ProjectsOutput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;

public class GetProjectsTask extends BaseTask <ProjectsOutput> {
    int page,pagesize;
    public GetProjectsTask(Context context,int page,int pagesize, @Nullable ApiListener<ProjectsOutput> listener) {
        super(context, listener);
        this.page = page;
        this.pagesize = pagesize;
    }

    @Override
    protected ProjectsOutput callApiMethod() throws Exception {
        return mApi.getProjects(page,pagesize);
    }
}
