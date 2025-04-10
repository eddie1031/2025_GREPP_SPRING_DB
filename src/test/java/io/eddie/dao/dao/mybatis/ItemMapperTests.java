package io.eddie.dao.dao.mybatis;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ItemMapperTests {

    @Autowired ItemMapper itemMapper;

    @Test
    @DisplayName("create_test")
    void save_test() throws Exception {

        Items item = Items.builder()
                .itemCode(TestUtils.genRandomItemCode())
                .name(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        itemMapper.save(item);

    }


}