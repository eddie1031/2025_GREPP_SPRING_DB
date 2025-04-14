package io.eddie.dao.dao.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.eddie.dao.global.entity.Items;
import io.eddie.dao.global.entity.QItems;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static io.eddie.dao.global.entity.QItems.*;


@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class QueryDslItemRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public void save(Items item) {
        entityManager.persist(item);
    }

    public Optional<Items> findByItemCode(String itemCode) {

//        String s = "select i from Items i where i.itemCode = :itemCode";

        Items findItem = queryFactory.selectFrom(items)
                .where(items.itemCode.eq(itemCode))
                .fetchFirst();


        return Optional.ofNullable(findItem);
    }

}
