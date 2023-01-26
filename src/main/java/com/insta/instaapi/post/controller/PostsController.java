package com.insta.instaapi.post.controller;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/user/post")
    public ResponseEntity<String> post(HttpServletRequest servletRequest, @RequestBody PostRequest requests) {
        String response = "";

        try {
            response = postsService.post(servletRequest, requests);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
