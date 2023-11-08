package com.anstudio.travelblog.http;

import com.anstudio.travelblog.http.Blog;

import java.util.List;

public interface BlogArticlesCallback {
    void onSuccess(List<Blog> blogList);
    void onError();
}
