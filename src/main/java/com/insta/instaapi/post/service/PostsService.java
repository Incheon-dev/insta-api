package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostRequest;

import javax.servlet.http.HttpServletRequest;

public interface PostsService {
    String post(HttpServletRequest servletRequest, PostRequest requests);
}
