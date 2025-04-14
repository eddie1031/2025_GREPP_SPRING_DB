package io.eddie.dao.global.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @Id
    @Column(name = "order_items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    private Orders orders;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "items_code", referencedColumnName = "code")
    private Items items;

    @Builder
    public OrderItems(Integer quantity, Orders orders, Items items) {
        this.quantity = quantity;
        this.orders = orders;
        this.items = items;
    }

}


