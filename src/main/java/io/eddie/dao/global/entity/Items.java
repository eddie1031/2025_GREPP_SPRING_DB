package io.eddie.dao.global.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class Items {

    private Long id;
    private String name;
    private String itemCode;

    private Integer price;

    private LocalDateTime createdAt;

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
