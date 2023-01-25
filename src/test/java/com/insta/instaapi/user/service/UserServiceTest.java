package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void 회원가입을_한다() throws Exception {
        //given
        final String phoneNumber = "admin-phoneNumber";
        final String email = "incheon-dev@gmail.com";
        final String name = "incheon-dev";
        final String nickname = "incheon-dev";
        final String password = "admin1234";
        final String introduction = "이것은 어드민 공용 테스트 계정";
        final String sex = "남자";

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
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, request, String.class);

        //then
        Users user = userRepository.findByEmail(email).orElse(null);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
    }
}
