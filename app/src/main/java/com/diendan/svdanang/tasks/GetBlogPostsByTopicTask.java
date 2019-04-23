package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.api.models.ProjectsOutput;

public class GetBlogPostsByTopicTask extends BaseTask <BlogPostsOutput> {
    int page,pagesize;
    Long idtopic;
    public GetBlogPostsByTopicTask(Context context,Long idtopic, int page, int pagesize, @Nullable ApiListener<BlogPostsOutput> listener) {
        super(context, listener);
        this.page = page;
        this.pagesize = pagesize;
        this.idtopic = idtopic;
    }

    @Override
    protected BlogPostsOutput callApiMethod() throws Exception {
        return mApi.getBlogPostsByIdTopic(idtopic,page,pagesize);
    }
}
