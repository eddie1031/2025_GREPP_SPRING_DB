package io.eddie.dao.dao.datajpa;

import io.eddie.dao.global.entity.Orders;
import io.eddie.dao.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataJpaOrderRepositoryTests {

    @Autowired
    DataJpaOrderRepository repository;

    @Test
    @DisplayName("save test")
    void save_test() throws Exception {

        String orderCode = TestUtils.genRandomOrderCode();
        Orders order = Orders.builder().orderCode(orderCode).build();

        Orders saved = repository.save(order);

        assertThat(saved.getId()).isNotNull();

    }


}