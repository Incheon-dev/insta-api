package com.insta.instaapi.post.controller;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
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
    public ResponseEntity<String> post(HttpServletRequest httpServletRequest, @RequestBody PostRequest requests) {
        String response = "";

        try {
            response = postsService.post(httpServletRequest, requests);
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

    @GetMapping("/api/user/post/{id}")
    public ResponseEntity<PostResponse> userPost(HttpServletRequest httpServletRequest, @PathVariable String id) {
        PostResponse response = null;

        try {
            response = postsService.userPost(httpServletRequest, id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
