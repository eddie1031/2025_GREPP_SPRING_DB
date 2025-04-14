package io.eddie.dao.dao.datajpa;

import io.eddie.dao.global.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaItemRepository extends JpaRepository<Items, Long> {

    // Query Method
    Optional<Items> findByItemCode(String itemCode);

}
