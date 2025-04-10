package io.eddie.dao.dao.hibernate;

import io.eddie.dao.global.entity.Items;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateItemRepository {

    private final EntityManager entityManager;

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



}
