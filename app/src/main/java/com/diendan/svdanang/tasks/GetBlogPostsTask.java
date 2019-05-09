package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostsOutput;

public class GetBlogPostsTask extends BaseTask <BlogPostsOutput> {
    int page,pagesize;
    public GetBlogPostsTask(Context context, int page, int pagesize, @Nullable ApiListener<BlogPostsOutput> listener) {
        super(context, listener);
        this.page = page;
        this.pagesize = pagesize;
    }

    @Override
    protected BlogPostsOutput callApiMethod() throws Exception {
        return mApi.getBlogPosts(page,pagesize);
    }
}
