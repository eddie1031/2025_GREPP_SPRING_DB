package io.eddie.dao.dao.hibernate;

import io.eddie.dao.dao.mybatis.ItemMapper;
import io.eddie.dao.global.entity.Items;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class HibernateItemRepository {

    private final EntityManager entityManager;
    private final ItemMapper itemMapper;

    public Items save(Items items) {
        entityManager.persist(items);
        return items;
    }

    public Optional<Items> findById(Long id) {
        Items items = entityManager.find(Items.class, id);
        return Optional.ofNullable(items);
    }

    public Optional<Items> findByItemCode(String itemCode) {

        try {

            String jpql = "select i from Items i where i.itemCode = :itemCode";

            Items findItem = entityManager.createQuery(jpql, Items.class)
                    .setParameter("itemCode", itemCode)
                    .getSingleResult();

            return Optional.of(findItem);

        } catch ( NoResultException e ) {
            return Optional.empty();
        }

    }

    public void updatePrice(String itemCode, Integer price) {
        Optional<Items> itemOptional = findByItemCode(itemCode);

        Items item = itemOptional.orElseThrow();
        item.setPrice(price);
    }

    public List<Items> saveAll(List<Items> items) {

        // buffer
        int batchSize = 50;

        for ( int i = 0; i < items.size(); i++ ) {

            entityManager.persist(items.get(i));

            if ( i % batchSize == 0 && i > 0 ) {
                entityManager.flush();
                entityManager.clear();
                log.info("FLUSH!");
            }

        }

        entityManager.flush();
        entityManager.clear();
        log.info("FLUSH!");

        return items;

    }



}

