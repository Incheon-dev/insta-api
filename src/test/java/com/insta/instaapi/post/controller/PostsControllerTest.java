package com.insta.instaapi.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.utils.security.config.SecurityConfig;
import com.insta.instaapi.utils.security.jwt.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PostsController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfig.class)
public class PostsControllerTest {

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtSecurityConfig jwtSecurityConfig;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 글_등록을_한다() throws Exception {
        List<String> photos = new ArrayList<>();
        photos.add("aws-s3-bucket-another-image.png");
        String content = objectMapper.writeValueAsString(new PostRequest(photos, "글 내용", "위치", true, true));

        mockMvc.perform(post("/api/user/post")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
