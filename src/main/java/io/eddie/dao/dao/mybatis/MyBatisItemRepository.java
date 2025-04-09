package io.eddie.dao.dao.mybatis;

import io.eddie.dao.global.entity.Items;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository {

    private final ItemMapper mapper;

    public void save(Items items) {
        mapper.save(items);
    }

    public void updatePrice(Long id, Integer price) {
        mapper.update(id, price);
    }

    public Items findByItemCode(String itemCode) {
        return mapper.findByItemCode(itemCode);
    }

    public void delete(Items items) {
        mapper.remove(items);
    }

}
