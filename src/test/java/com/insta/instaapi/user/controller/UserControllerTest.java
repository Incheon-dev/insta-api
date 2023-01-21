package com.insta.instaapi.user.controller;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void 유저가_생성된다() throws Exception {
        //given
        String phoneNumber = "phoneNumber1";
        String email = "email1";
        String name = "name1";
        String nickname = "nickname1";
        String password = "{BCrypt}password1";
        String introduction = "introduction1";
        String sex = "sex1";

        SignUpRequest request = SignUpRequest.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .introduction(introduction)
                .sex(sex)
                .build();

        String url = "http://localhost:" + port + "/api/account/sign-up";

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Users> users = userRepository.findAll();

        assertThat(users.get(0).getName()).isEqualTo(name);
        assertThat(users.get(0).getPassword()).isEqualTo(password);
    }

    @Test
    public void 이미_가입한_유저가_있다() throws Exception {
        //given
        String phoneNumber = "phoneNumber1";
        String email = "email1";
        String name = "name1";
        String nickname = "nickname1";
        String password = "{BCrypt}password1";
        String introduction = "introduction1";
        String sex = "sex1";

        Users user = Users.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .introduction(introduction)
                .sex(sex)
                .build();

        userRepository.save(user);

        SignUpRequest request = SignUpRequest.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .introduction(introduction)
                .sex(sex)
                .build();

        String url = "http://localhost:" + port + "/api/account/sign-up";

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);

        //then
        assertThat(responseEntity.getBody()).isEqualTo("해당 번호로 가입된 유저가 존재합니다.");
    }

    @Test
    public void 유저가_존재하는지_검증한다() throws Exception {
        //given
        String phoneNumber = "phoneNumber1";
        String email = "email1";
        String name = "name1";
        String nickname = "nickname1";
        String password = "{BCrypt}password1";
        String introduction = "introduction1";
        String sex = "sex1";

        Users user = Users.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .introduction(introduction)
                .sex(sex)
                .build();

        userRepository.save(user);

        String url = "http://localhost:" + port + "/api/account?email={email}";

        //when
        Boolean response = restTemplate.getForObject(url, Boolean.class, email);

        //then
        assertThat(response).isEqualTo(true);
    }
}