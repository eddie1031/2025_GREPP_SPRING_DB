package io.eddie.dao.util;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.global.entity.OrderItems;
import io.eddie.dao.global.entity.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestUtils {

    private static final String ITEM = "ITEM_";
    private static final String ORDER = "ORDER_";

    public static String genRandomItemCode() {
        return ITEM + genNumStr();
    }

    public static String genRandomOrderCode() {
        return ORDER + genNumStr();
    }

    public static Integer genRandomPrice() {
        int num = (int) (Math.random() * 100_000);
        return num * 10_000;
    }

    public static Integer genRandomQuantity() {
        return (int) (Math.random() * 100);
    }

    private static String genNumStr() {
        int num = (int) (Math.random() * 100_000);
        return Integer.toString(num);
    }

    public static Items generateItem() {
        return Items.builder()
                .name(genRandomItemCode())
                .itemCode(genRandomItemCode())
                .price(genRandomPrice())
                .build();
    }

    public static List<Items> generateItems(int amount) {
        List<Items> items = new ArrayList<>();

        for ( int i = 0; i < amount; i++ ) {
            items.add(generateItem());
        }

        return items;
//        return  IntStream.range(0, amount)
//                .mapToObj(i -> generateItem())
//                .toList();
    }

    public static List<OrderItems> genOrderItems(Orders order, List<Items> items, int amount) {
            return items.stream()
                    .map( i -> OrderItems.builder()
                            .orders(order)
                            .quantity(amount)
                            .items(i)
                            .build()
                    ).toList();
    }


}
