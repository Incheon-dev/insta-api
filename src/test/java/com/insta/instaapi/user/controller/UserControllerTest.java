package com.insta.instaapi.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import com.insta.instaapi.user.service.UserServiceImpl;
import com.insta.instaapi.utils.security.config.SecurityConfig;
import com.insta.instaapi.utils.security.dto.SignInRequest;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
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
    private MockMvc mockMvc;

    @Test
    public void 회원가입을_한다() throws Exception {
        String content = objectMapper.writeValueAsString(new SignUpRequest("010-7184-2939", "csd_1996@naver.com", "최승대",
                "ChoiDevv", "profileImage.png", "admin1234", "최승대입니다.", "남자"));

        mockMvc.perform(post("/api/account/sign-up")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 비밀번호를_변경한다() throws Exception {
        String content = objectMapper.writeValueAsString(new UpdatePasswordRequest("csd_1996@naver.com", "new_admin1234"));

        mockMvc.perform(put("/api/account")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 이메일을_검증한다() throws Exception {
        String email = "csd_1996@naver.com";

        mockMvc.perform(get("/api/account")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 로그인을_한다() throws Exception {
        String content = objectMapper.writeValueAsString(new SignInRequest("csd_1996@naver.com", "admin1234"));

        mockMvc.perform(post("/api/account/authenticate")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 유저를_검색한다() throws Exception {
        //given
        String email = "csd_1996@naver.com";

        UserResponse response = UserResponse.builder()
                        .id("id")
                        .email("email")
                        .name("name")
                        .nickname("nickname")
                        .introduction("introduction")
                        .sex("sex")
                        .build();

        //when
        given(userService.search(any(), any())).willReturn(response);

        //then
        mockMvc.perform(get("/api/user/search")
                        .param("email", email))
                .andExpect(status().isOk())
                .andDo(print());
    }
}