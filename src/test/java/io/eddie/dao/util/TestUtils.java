package io.eddie.dao.util;

public class TestUtils {

    private static final String ITEM = "ITEM_";

    public static String genRandomItemCode() {
        return ITEM + genNumStr();
    }

    public static Integer genRandomPrice() {
        int num = (int) (Math.random() * 100_000);
        return num * 10_000;
    }

    private static String genNumStr() {
        int num = (int) (Math.random() * 100_000);
        return Integer.toString(num);
    }

}
