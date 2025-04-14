package io.eddie.dao.dao.hibernate;

import io.eddie.dao.global.entity.Orders;
import io.eddie.dao.util.TestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class HibernateOrderRepositoryTests {

    @Autowired
    HibernateOrderRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("주문 저장 테스트")
    void save_order_test() throws Exception {

        String orderCode = TestUtils.genRandomOrderCode();
        Orders order = Orders.builder()
                .orderCode(orderCode)
                .build();

        Orders saved = repository.saveOrder(order);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOrderCode()).isEqualTo(orderCode);
        assertThat(saved.getOrderCode()).isEqualTo(order.getOrderCode());

    }

    @Test
    @DisplayName("주문 저장 오류 테스트")
    void save_order_test_ng() throws Exception {

        Orders order = Orders.builder()
                .build();

        assertThatThrownBy(
                () -> {
                    repository.saveOrder(order);
                }
        ).isInstanceOf(Exception.class);


    }







}