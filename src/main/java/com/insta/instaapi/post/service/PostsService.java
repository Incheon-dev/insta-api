package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostsService {
    String post(HttpServletRequest httpServletRequest, PostRequest requests);

    List<PostResponse> userPosts(HttpServletRequest httpServletRequest, String email);

    PostResponse userPost(HttpServletRequest httpServletRequest, String postId);

    List<InfoResponse> allPosts(HttpServletRequest httpServletRequest);
}
