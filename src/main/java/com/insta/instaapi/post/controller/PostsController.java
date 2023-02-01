package com.insta.instaapi.post.controller;

import com.insta.instaapi.post.dto.request.PostCommentRequest;
import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.request.UpdatePostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostCommentResponse;
import com.insta.instaapi.post.dto.response.PostResponse;
import com.insta.instaapi.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/user/post")
    public ResponseEntity<String> post(HttpServletRequest httpServletRequest, @RequestBody PostRequest request) {
        String response = "";

        try {
            response = postsService.post(httpServletRequest, request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/posts")
    public ResponseEntity<List<InfoResponse>> allPosts(HttpServletRequest httpServletRequest) {
        List<InfoResponse> response = null;

        try {
            response = postsService.allPosts(httpServletRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/posts/{email}")
    public ResponseEntity<List<PostResponse>> userPosts(HttpServletRequest httpServletRequest, @PathVariable String email) {
        List<PostResponse> response = null;

        try {
            response = postsService.userPosts(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/post/{postId}")
    public ResponseEntity<PostResponse> userPost(HttpServletRequest httpServletRequest, @PathVariable String postId) {
        PostResponse response = null;

        try {
            response = postsService.userPost(httpServletRequest, postId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/user/post")
    public ResponseEntity<?> updatePost(HttpServletRequest httpServletRequest, @RequestBody UpdatePostRequest request) {
        String response = "";

        try {
            response = postsService.updatePost(httpServletRequest, request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/user/post/{postId}")
    public ResponseEntity<?> deletePost(HttpServletRequest httpServletRequest, @PathVariable String postId) {
        String response = "";

        try {
            response = postsService.deletePost(httpServletRequest, postId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
         return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/user/post/{postId}/comment")
    public ResponseEntity<?> postComment(HttpServletRequest httpServletRequest, @PathVariable String postId, @RequestBody PostCommentRequest request) {
        String response = "";

        try {
            response = postsService.postComment(httpServletRequest, postId, request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/post/{postId}/comment")
    public ResponseEntity<?> userComment(HttpServletRequest httpServletRequest, @PathVariable String postId) {
        List<PostCommentResponse> response = null;

        try {
            response = postsService.userComment(httpServletRequest, postId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
