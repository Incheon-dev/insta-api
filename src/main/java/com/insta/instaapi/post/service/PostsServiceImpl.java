package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.repository.PostsRepository;
import com.insta.instaapi.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final UserServiceImpl userService;
    private final PostsRepository postsRepository;

    @Override
    public String post(HttpServletRequest servletRequest, PostRequest requests) {
        String postId = "";

        for (String photo: requests.getPhotos()) {
            postId = postsRepository.save(new Posts().create(requests, userService.current(servletRequest), photo)).getId();
        }

        return postId;
    }
}
