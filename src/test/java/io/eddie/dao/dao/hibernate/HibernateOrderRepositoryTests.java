package io.eddie.dao.dao.hibernate;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.global.entity.OrderItems;
import io.eddie.dao.global.entity.Orders;
import io.eddie.dao.util.TestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@Transactional
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

    @Test
    @DisplayName("주문 코드로 주문 조회")
    void find_order_by_order_code_test() throws Exception {

        List<Items> items = TestUtils.generateItems(5);

        for (Items item : items) {
            entityManager.persist(item);
        }

        Orders order = Orders.builder()
                .orderCode(TestUtils.genRandomOrderCode())
                .build();

        repository.saveOrder(order);

        List<OrderItems> orderItems = TestUtils.genOrderItems(order, items, 5);
        repository.saveAllOrderItems(orderItems);

        Optional<Orders> orderOptional = repository.findOrderByOrderCode(order.getOrderCode());
        assertThat(orderOptional.isPresent()).isTrue();

        Orders findOrder = orderOptional.get();
        assertThat(findOrder.getId()).isNotNull();

        findOrder.getItems()
                .forEach(
                        i -> {
                            log.info("{} = {}" ,i.getItems().getName(), i.getQuantity());
                        }
                );

    }





}