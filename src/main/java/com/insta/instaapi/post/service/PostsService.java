package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostsService {
    String create(HttpServletRequest httpServletRequest, PostRequest requests);

    List<PostResponse> posts(HttpServletRequest httpServletRequest, String email);

    PostResponse post(HttpServletRequest httpServletRequest, String postId);

    List<InfoResponse> posts(HttpServletRequest httpServletRequest);
}
