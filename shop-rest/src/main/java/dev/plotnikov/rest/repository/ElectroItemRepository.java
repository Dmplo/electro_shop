package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.ElectroItem;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElectroItemRepository extends JpaRepository<ElectroItem, Long> {

    @Query("select e from ElectroItem e where e.typeId = :typeId")
    Page<ElectroItem> findByType(@Param("typeId") Long typeId, Pageable pageable);

    @Query("select e from ElectroItem e")
    Page<ElectroItem> findAllCustom(Pageable pageable);

}
