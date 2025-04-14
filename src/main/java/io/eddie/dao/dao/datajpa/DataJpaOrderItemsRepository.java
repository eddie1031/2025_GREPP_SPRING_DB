package io.eddie.dao.dao.datajpa;

import io.eddie.dao.global.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.invoke.CallSite;
import java.util.List;

public interface DataJpaOrderItemsRepository extends JpaRepository<OrderItems, Long> {

    @Query("select oi from OrderItems oi where oi.orders.orderCode = :orderCode")
    List<OrderItems> findAllByOrderCode(String orderCode);

}
