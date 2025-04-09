package io.eddie.dao.dao.mybatis;

import io.eddie.dao.global.entity.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {

    void save(Items items);
    void update(@Param("id") Long id, @Param("price") Integer price);
    Items findByItemCode(@Param("itemCode")String itemCode);
    void remove(Items items);

}
