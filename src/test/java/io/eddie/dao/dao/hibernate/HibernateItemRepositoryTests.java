package io.eddie.dao.dao.hibernate;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class HibernateItemRepositoryTests {

    @Autowired
    HibernateItemRepository repository;

    @Test
    @DisplayName("Item 저장")
    void save_item_test() throws Exception {

        Items item1 = Items.builder()
                .itemCode(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        Items saved = repository.save(item1);
        assertThat(saved.getId()).isNotNull();

        Items item2 = Items.builder()
                .price(TestUtils.genRandomPrice())
                .build();

        assertThatThrownBy(
                () -> {
                    repository.save(item2);
                }
        ).isInstanceOf(DataIntegrityViolationException.class);

    }

    @Test
    @DisplayName("item 조회")
    void select_item_test() throws Exception {

        String itemCode = TestUtils.genRandomItemCode();

        Items item = Items.builder()
                .name(itemCode)
                .itemCode(itemCode)
                .price(TestUtils.genRandomPrice())
                .build();

        repository.save(item);

        Optional<Items> itemsOptional = repository.findByItemCode(itemCode);
        assertThat(itemsOptional.isPresent()).isTrue();

        Items findItem = itemsOptional.get();
        assertThat(findItem.getItemCode()).isEqualTo(itemCode);

    }

    @Test
    @DisplayName("item 조회 2")
    void select_item_test_2() throws Exception {

        String targetItemCode = "INVALID";
        String itemCode = TestUtils.genRandomItemCode();

        Items item = Items.builder()
                .name(itemCode)
                .itemCode(itemCode)
                .price(TestUtils.genRandomPrice())
                .build();

        repository.save(item);

        Optional<Items> itemsOptional = repository.findByItemCode(targetItemCode);
        assertThat(itemsOptional.isPresent()).isFalse();

        assertThatThrownBy(
                () -> {
                    itemsOptional.get();
                }
        ).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    @DisplayName("Item 수정")
    void update_item_test() throws Exception {

        String itemCode = TestUtils.genRandomItemCode();
        Integer TARGET_PRICE = 10;

        Items item1 = Items.builder()
                .name(itemCode)
                .itemCode(itemCode)
                .price(TestUtils.genRandomPrice())
                .build();

        repository.save(item1);

        Optional<Items> itemsOptional = repository.findByItemCode(itemCode);
        assertThat(itemsOptional.isPresent()).isTrue();

        Items findItem = itemsOptional.get();

        repository.updatePrice(findItem.getItemCode(), TARGET_PRICE);

        Optional<Items> itemsOptional2 = repository.findByItemCode(itemCode);
        assertThat(itemsOptional2.isPresent()).isTrue();

        Items updatedItem = itemsOptional2.get();
        assertThat(updatedItem.getPrice()).isEqualTo(TARGET_PRICE);

    }

    @Test
    @DisplayName("save all 테스트")
    void save_all_test() throws Exception {

        List<Items> items = TestUtils.generateItems(150);

        repository.saveAll(items);


    }






}