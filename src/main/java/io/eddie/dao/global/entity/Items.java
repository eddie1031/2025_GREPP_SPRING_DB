package io.eddie.dao.global.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(nullable = false, unique = true)
    private String itemCode;

    @Setter
    private Integer price;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Items(String name, String itemCode, Integer price) {
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) && Objects.equals(name, items.name) && Objects.equals(itemCode, items.itemCode) && Objects.equals(price, items.price) && Objects.equals(createdAt, items.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, itemCode, price, createdAt);
    }

}
