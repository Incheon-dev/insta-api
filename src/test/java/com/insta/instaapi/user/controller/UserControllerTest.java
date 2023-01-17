package com.insta.instaapi.user.controller;

import com.insta.instaapi.user.dto.SignUpRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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
        String username = "user1";
        String password = "{BCrypt}password1";
        SignUpRequest request = SignUpRequest.builder()
                .username(username)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/v1/sign-up";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> users = userRepository.findAll();
        assertThat(users.get(0).getUsername()).isEqualTo(username);
        assertThat(users.get(0).getPassword()).isEqualTo(password);
    }
}
