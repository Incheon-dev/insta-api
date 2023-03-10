package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostCommentRequest;
import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.request.UpdateCommentRequest;
import com.insta.instaapi.post.dto.request.UpdatePostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostCommentResponse;
import com.insta.instaapi.post.dto.response.PostResponse;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostsService {
    String post(HttpServletRequest httpServletRequest, PostRequest request);

    List<PostResponse> userPosts(HttpServletRequest httpServletRequest, String email, Pageable pageable);

    PostResponse userPost(HttpServletRequest httpServletRequest, String postId);

    List<InfoResponse> allPosts(HttpServletRequest httpServletRequest, Pageable pageable);

    String updatePost(HttpServletRequest httpServletRequest, UpdatePostRequest request);

    String deletePost(HttpServletRequest httpServletRequest, String postId);

    String postComment(HttpServletRequest httpServletRequest, String postId, PostCommentRequest request);

    List<PostCommentResponse> userComment(HttpServletRequest httpServletRequest, String postId, Pageable pageable);

    String postLike(HttpServletRequest httpServletRequest, String postId);

    String commentLike(HttpServletRequest httpServletRequest, String postId, String commentId);

    String updateComment(HttpServletRequest httpServletRequest, String postId, UpdateCommentRequest request);

    String deleteComment(HttpServletRequest httpServletRequest, String postId, String commentId);
}
