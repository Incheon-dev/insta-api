package com.insta.instaapi.post.service;

import com.insta.instaapi.post.dto.request.PostCommentRequest;
import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.request.UpdateCommentRequest;
import com.insta.instaapi.post.dto.request.UpdatePostRequest;
import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostCommentResponse;
import com.insta.instaapi.post.dto.response.PostResponse;
import com.insta.instaapi.post.entity.*;
import com.insta.instaapi.post.entity.repository.*;
import com.insta.instaapi.post.entity.repository.queryDSL.DslPostsRepository;
import com.insta.instaapi.post.exception.PostCommentException;
import com.insta.instaapi.post.exception.PostException;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final PostsCommentsRepository postsCommentsRepository;
    private final PostsLikeRepository postsLikeRepository;
    private final PostsCommentsLikeRepository postsCommentsLikeRepository;
    private final DslPostsRepository dslPostsRepository;

    @Override
    public String post(HttpServletRequest httpServletRequest, PostRequest request) {
        Posts post = postsRepository.save(new Posts().create(request, current(httpServletRequest), Status.NOT_DELETED));
        savePhotos(post, request.getPhotos());
        return post.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public PostResponse userPost(HttpServletRequest httpServletRequest, String postId) {
        Posts post = findById(postId);
        Long countComment = postsCommentsRepository.countByPostsAndPostsCommentsStatus(post, Status.NOT_DELETED);
        Long countLike = postsLikeRepository.countByPosts(post);

        return PostResponse.of(post, photos(post), countComment, countLike);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InfoResponse> allPosts(HttpServletRequest httpServletRequest, Pageable pageable) {
        List<InfoResponse> info = dslPostsRepository.followingPosts(current(httpServletRequest).getId(), pageable);
        List<InfoResponse> result = new ArrayList<>();

        for (InfoResponse info1: info) {
            List<String> photos = postsPhotosRepository.findAllByPosts(postsRepository.findById(info1.getPostId())
                    .orElseThrow(() -> new PostException("???????????? ?????? ??? ????????????."))).stream().map(photo -> photo.getPhoto()).collect(Collectors.toList());
            result.add(InfoResponse.of(info1, photos));
        }
        return result;
    }

    @Override
    public String updatePost(HttpServletRequest httpServletRequest, UpdatePostRequest request) {
        Posts post = findById(request.getPostId());
        post.update(request);
        updatePhotos(post, request.getPhotos());

        return "?????????????????????.";
    }

    @Override
    public String deletePost(HttpServletRequest httpServletRequest, String postId) {
        Posts post = findById(postId);
        List<PostsComments> comments = postsCommentsRepository.findByPosts(post);

        post.delete(Status.DELETED);
        for (PostsComments comment: comments) {
            comment.delete(Status.DELETED);
        }

        return "?????????????????????.";
    }

    @Override
    public String postComment(HttpServletRequest httpServletRequest, String postId, PostCommentRequest request) {
        Posts post = findById(postId);
        return postsCommentsRepository.save(new PostsComments(current(httpServletRequest), post, request.getPostsCommentContent(), Status.NOT_DELETED)).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostCommentResponse> userComment(HttpServletRequest httpServletRequest, String postId, Pageable pageable) {
        return dslPostsRepository.postComments(postId, pageable);
    }

    @Override
    public String postLike(HttpServletRequest httpServletRequest, String postId) {
        Posts post = findById(postId);

        if (isClickedPostLike(current(httpServletRequest), post)) {
            return cancelPostLike(current(httpServletRequest), post);
        }

        postsLikeRepository.save(new PostsLike(current(httpServletRequest), post));
        return "?????????";
    }

    @Override
    public String commentLike(HttpServletRequest httpServletRequest, String postId, String commentId) {
        PostsComments comment = postsCommentsRepository.findById(commentId)
                .orElseThrow(() -> new PostCommentException("????????? ?????? ??? ????????????."));

        if (isClickedCommentLike(current(httpServletRequest), comment)) {
            return cancelCommentLike(current(httpServletRequest), comment);
        }

        postsCommentsLikeRepository.save(new PostsCommentsLike(current(httpServletRequest), comment));
        return "?????????";
    }

    @Override
    public String updateComment(HttpServletRequest httpServletRequest, String postId, UpdateCommentRequest request) {
        PostsComments comment = postsCommentsRepository.findById(request.getCommentId())
                .orElseThrow(() -> new PostCommentException("????????? ?????? ??? ????????????."));

        comment.update(request.getPostsCommentsContent());

        return "?????????????????????.";
    }

    @Override
    public String deleteComment(HttpServletRequest httpServletRequest, String postId, String commentId) {
        PostsComments comment = postsCommentsRepository.findById(commentId)
                .orElseThrow(() -> new PostCommentException("????????? ?????? ??? ????????????."));

        comment.delete(Status.DELETED);

        return "?????????????????????.";
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> userPosts(HttpServletRequest httpServletRequest, String email, Pageable pageable) {
        List<Posts> posts = postsRepository.findByUsersAndPostsStatus(findByEmail(email), Status.NOT_DELETED, pageable);
        List<PostResponse> result = new ArrayList<>();

        for (Posts post: posts) {
            Long countComment = postsCommentsRepository.countByPostsAndPostsCommentsStatus(post, Status.NOT_DELETED);
            Long countLike = postsLikeRepository.countByPosts(post);
            result.add(PostResponse.of(post, photos(post), countComment, countLike));
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

    private Posts findById(String postId) {
        return postsRepository.findByIdAndPostsStatus(postId, Status.NOT_DELETED)
                .orElseThrow(() -> new PostException("???????????? ?????? ??? ????????????."));
    }

    private boolean isClickedPostLike(Users user, Posts post) {
        return postsLikeRepository.existsByUsersAndPosts(user, post);
    }

    private String cancelPostLike(Users user, Posts post) {
        postsLikeRepository.deleteByUsersAndPosts(user, post);
        return "????????? ??????";
    }

    private boolean isClickedCommentLike(Users user, PostsComments comment) {
        return postsCommentsLikeRepository.existsByUsersAndPostsComments(user, comment);
    }

    private String cancelCommentLike(Users user, PostsComments comment) {
        postsCommentsLikeRepository.deleteByUsersAndPostsComments(user, comment);
        return "????????? ??????";
    }
}
