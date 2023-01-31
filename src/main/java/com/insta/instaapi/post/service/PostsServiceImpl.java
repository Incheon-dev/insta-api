package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.request.UpdatePostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostResponse;
import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsPhotos;
import com.insta.instaapi.post.entity.PostsStatus;
import com.insta.instaapi.post.entity.repository.PostsPhotosRepository;
import com.insta.instaapi.post.entity.repository.PostsRepository;
import com.insta.instaapi.post.entity.repository.queryDSL.DslPostsRepository;
import com.insta.instaapi.post.exception.PostException;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final UserServiceImpl userService;
    private final PostsRepository postsRepository;
    private final PostsPhotosRepository postsPhotosRepository;
    private final DslPostsRepository dslPostsRepository;

    @Override
    public String post(HttpServletRequest httpServletRequest, PostRequest request) {
        Posts post = postsRepository.save(new Posts().create(request, current(httpServletRequest), PostsStatus.NOT_DELETED));
        savePhotos(post, request.getPhotos());
        return post.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public PostResponse userPost(HttpServletRequest httpServletRequest, String postId) {
        Posts post = postsRepository.findByIdAndPostsStatus(postId, PostsStatus.NOT_DELETED)
                .orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다."));

        return PostResponse.toEntity(post, photos(post));
    }

    @Transactional(readOnly = true)
    @Override
    public List<InfoResponse> allPosts(HttpServletRequest httpServletRequest) {
        List<InfoResponse> info = dslPostsRepository.followingPosts(current(httpServletRequest).getId());
        List<InfoResponse> result = new ArrayList<>();

        for (InfoResponse info1: info) {
            List<String> photos = postsPhotosRepository.findAllByPosts(postsRepository.findById(info1.getPostId())
                    .orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다."))).stream().map(photo -> photo.getPhoto()).collect(Collectors.toList());
            result.add(InfoResponse.of(info1, photos));
        }
        return result;
    }

    @Override
    public String updatePost(HttpServletRequest httpServletRequest, UpdatePostRequest request) {
        Posts post = postsRepository.findByIdAndPostsStatus(request.getPostId(), PostsStatus.NOT_DELETED)
                .orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다."));
        post.update(request);
        updatePhotos(post, request.getPhotos());

        return "수정되었습니다.";
    }

    @Override
    public String deletePost(HttpServletRequest httpServletRequest, String id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다."));
        post.delete(PostsStatus.DELETED);

        return "삭제되었습니다.";
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> userPosts(HttpServletRequest httpServletRequest, String email) {
        List<Posts> posts = postsRepository.findByUsersAndPostsStatus(findByEmail(email), PostsStatus.NOT_DELETED);
        List<PostResponse> result = new ArrayList<>();

        for (Posts post: posts) {
            result.add(PostResponse.of(post, photos(post)));
        }
        return result;
    }

    private Users current(HttpServletRequest httpServletRequest) {
        return userService.current(httpServletRequest);
    }

    private void savePhotos(Posts post, List<String> photos) {
        for (String photo: photos) {
            postsPhotosRepository.save(new PostsPhotos(post, photo));
        }
    }

    private void updatePhotos(Posts post, List<String> photos) {
        postsPhotosRepository.deleteAllByPosts(post);
        savePhotos(post, photos);
    }

    private List<String> photos(Posts post) {
        return postsPhotosRepository.findAllByPosts(post).stream().map(photo -> photo.getPhoto()).collect(Collectors.toList());
    }

    private Users findByEmail(String email) {
        return userService.findByEmail(email);
    }
}
