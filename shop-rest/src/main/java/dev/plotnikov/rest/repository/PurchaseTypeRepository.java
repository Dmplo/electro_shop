package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {

}
