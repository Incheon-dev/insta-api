package com.insta.instaapi.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.service.UserServiceImpl;
import com.insta.instaapi.utils.jwt.config.*;
import com.insta.instaapi.utils.security.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfig.class)
public class UserControllerTest {

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

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void signup() throws Exception {
        String name = "유저";
        SignUpRequest request = SignUpRequest.builder()
                .phoneNumber("010-0000-0000")
                .email("test@naver.com")
                .name(name)
                .nickname("ChoiDevv")
                .profileImage(null)
                .password("test1234")
                .introduction("테스트")
                .sex("남성")
                .build();

        given(userService.signup(request)).willReturn(name);

        mockMvc.perform(post("/api/account/sign-up")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void validateEmail() throws Exception {
        Boolean response = false;
        String email = "test@naver.com";

        Users user = Users.builder()
                .phoneNumber("010-0000-0000")
                .email(email)
                .name("test")
                .nickname("test")
                .build();

        given(userService.validateEmail(email)).willReturn(response);

        mockMvc.perform(get("/api/account")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void resetPassword() {
    }

    @Test
    public void search() {
    }

    @Test
    public void block() {
    }

    @Test
    public void blockList() {
    }

    @Test
    public void unblock() {
    }

    @Test
    public void follow() {
    }

    @Test
    public void unfollow() {
    }

    @Test
    public void userInfo() {
    }

    @Test
    public void profileImage() {
    }

    @Test
    public void deleteProfileImage() {
    }
}
