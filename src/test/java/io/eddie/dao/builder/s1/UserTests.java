package io.eddie.dao.builder.s1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserTests {

    @Test
    @DisplayName("빌더 테스트")
    void builder_test() throws Exception {

        String name = "user";
        int age = 20;
        String email = "user@example.com";

        User user = User.builder()
                .email(email)
                .age(age)
                .name(name)
                .build();

        log.info("user.getName() = {}", user.getName());
        log.info("user.getAge() = {}", user.getAge());
        log.info("user.getEmail() = {}", user.getEmail());

    }

}