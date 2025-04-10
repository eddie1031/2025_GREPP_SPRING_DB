package io.eddie.dao.dao.mybatis;

import io.eddie.dao.global.entity.Items;
import io.eddie.dao.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MyBatisItemRepositoryTests {

    @Mock
    ItemMapper mapper;
    MyBatisItemRepository repository;

    @BeforeEach
    void init() {
        repository = new MyBatisItemRepository(mapper);
    }

    @Test
    @DisplayName("상품 등록 서비스")
    void item_save_test() throws Exception {

        Items item = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .itemCode(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        doNothing().when(mapper).save(item);

        Items saved = repository.save(item);

        assertThat(saved.getItemCode()).isEqualTo(item.getItemCode());
        verify(mapper, times(1)).save(item);

    }

    @Test
    @DisplayName("itemcode가 없는 item을 save하면 오류가 발생할 것이다.")
    void raise_exception_test_1() throws Exception {

        Items item = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        doThrow(RuntimeException.class).when(mapper).save(item);

        assertThatThrownBy(
                () -> {
                    repository.save(item);
                }
        ).isInstanceOf(RuntimeException.class);

        verify(mapper, times(1)).save(item);

    }

    @Test
    @DisplayName("유효한 itemcode는 item을 조회할 수 있고 그렇지 않으면 조회가 불가능하다")
    void find_by_item_code_test() throws Exception {

        String VALID_ITEM_CODE = TestUtils.genRandomItemCode();
        String INVALID_ITEM_CODE = "INVALID_ITEM_CODE";

        Items validItem = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .itemCode(VALID_ITEM_CODE)
                .price(TestUtils.genRandomPrice())
                .build();

        when(mapper.findByItemCode(VALID_ITEM_CODE)).thenReturn(Optional.of(validItem));
        when(mapper.findByItemCode(INVALID_ITEM_CODE)).thenReturn(Optional.empty());

        Optional<Items> validItemOptional = repository.findByItemCode(VALID_ITEM_CODE);
        assertThat(validItemOptional.isPresent()).isTrue();
        assertThat(validItemOptional.get()).isEqualTo(validItem);
        assertThat(validItemOptional.get().getItemCode()).isEqualTo(VALID_ITEM_CODE);

        Optional<Items> invalidItemOptional = repository.findByItemCode(INVALID_ITEM_CODE);
        assertThat(invalidItemOptional.isPresent()).isFalse();
        assertThatThrownBy(
                () -> {
                    invalidItemOptional.get();
                }
        ).isInstanceOf(NoSuchElementException.class);

    }


}