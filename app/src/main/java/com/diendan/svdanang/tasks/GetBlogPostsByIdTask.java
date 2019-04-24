package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.BlogPostsOutput;

public class GetBlogPostsByIdTask extends BaseTask <BlogPostOutput> {
    Long id;
    public GetBlogPostsByIdTask(Context context, Long id, @Nullable ApiListener<BlogPostOutput> listener) {
        super(context, listener);
        this.id = id;
    }

    @Override
    protected BlogPostOutput callApiMethod() throws Exception {
        return mApi.getBlogPostById(id);
    }
}
