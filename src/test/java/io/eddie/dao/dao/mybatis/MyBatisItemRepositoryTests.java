package io.eddie.dao.dao.mybatis;

import io.eddie.dao.global.entity.Items;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MyBatisItemRepositoryTests {

    @Test
    @DisplayName("상품 등록 서비스")
    void item_save_test() throws Exception {

        Items items = new Items("CODE", "NAME", 1000);
        log.info("items = {}", items);

    }

}