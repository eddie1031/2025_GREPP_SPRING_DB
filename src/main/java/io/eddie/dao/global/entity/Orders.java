package io.eddie.dao.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String orderCode;

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , orphanRemoval = true
            , mappedBy = "orders")
    private List<OrderItems> items = new ArrayList<>();

    private LocalDateTime orderedAt = LocalDateTime.now();

    @Builder
    public Orders(String orderCode, List<OrderItems> items) {
        this.orderCode = orderCode;
        if ( items != null ) {
            this.items = items;
        }
    }

}
