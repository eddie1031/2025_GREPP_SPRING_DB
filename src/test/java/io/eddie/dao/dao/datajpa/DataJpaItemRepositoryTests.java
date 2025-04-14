package io.eddie.dao.dao.datajpa;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.util.TestUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class DataJpaItemRepositoryTests {

    @Autowired
    DataJpaItemRepository repository;

    @Test
    @DisplayName("save 테스트")
    void save_test() throws Exception {

        Items item = TestUtils.generateItem();

        Items saved = repository.save(item);
        assertThat(saved.getId()).isNotNull();

    }

    @Test
    @DisplayName("findById 테스트")
    void find_by_id_test() throws Exception {

        Items item = TestUtils.generateItem();

        Items saved = repository.save(item);

        Optional<Items> itemOptional = repository.findById(saved.getId());
        assertThat(itemOptional.isPresent()).isTrue();

        assertThat(itemOptional.get()).isNotNull();

    }

    @Test
    @DisplayName("findByOrderCode 테스트")
    void find_by_order_code_test() throws Exception {
        Items item = TestUtils.generateItem();
        Items saved = repository.save(item);

        Optional<Items> itemsOptional = repository.findByItemCode(saved.getItemCode());
        assertThat(itemsOptional.isPresent()).isTrue();

        Items findItem = itemsOptional.get();

        assertThat(findItem.getItemCode()).isEqualTo(saved.getItemCode());
        assertThat(findItem.getItemCode()).isEqualTo(item.getItemCode());

    }



}