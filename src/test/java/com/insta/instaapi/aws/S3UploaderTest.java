package com.insta.instaapi.aws;

import com.insta.instaapi.utils.aws.S3Uploader;
import io.findify.s3mock.S3Mock;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Import(S3MockConfig.class)
public class S3UploaderTest {

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private S3Mock s3Mock;

    @Test
    public void 이미지_저장을_s3에_등록한다() throws IOException {
        String file = "mock1.png";
        String expected = "https://incheon-dev.s3.ap-northeast-2.amazonaws.com/static/" + file;
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file,
                "image/png", "test data".getBytes());
        String uploadImageUrl  = s3Uploader.upload(mockMultipartFile, "static");

        assertThat(uploadImageUrl).isEqualTo(expected);
    }

    @After
    public void shutdownMockS3(){
        s3Mock.stop();
    }
}
