package io.eddie.dao.builder.s0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTests {

    @Test
    @DisplayName("User 생성 테스트 #C1")
    void user_create_c1() throws Exception {

        String name = "user1";

        User user = new User(name);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(name);

    }


}