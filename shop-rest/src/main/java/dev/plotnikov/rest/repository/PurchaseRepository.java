package dev.plotnikov.rest.repository;


import dev.plotnikov.rest.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select count(p) from Purchase p")
    Long findPurchaseCount();

    @Query(value = "select extract(year from purchase_date) as date from purchase group by date order by date", nativeQuery = true)
    List<String> findDistinctYears();

}

