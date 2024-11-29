package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.ElectroShop;
import dev.plotnikov.rest.entity.ElectroShopPK;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectroShopRepository extends JpaRepository<ElectroShop, ElectroShopPK> {
    @Query("select es from ElectroShop es where es.shopId = :shopId and es.count > 0")
    List<ElectroShop> findByShop(@Param("shopId") Long shopId);
}
