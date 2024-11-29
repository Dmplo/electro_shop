package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.PurchaseRatingCountShopDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRatingCountShopRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QueryBuilderReportRatingShop builder;
    public List<PurchaseRatingCountShopDTO> findCountShopRating(RequestParams params) {

        String nativeQuery = builder.buildQuery(params, "count_");
        return entityManager.createNativeQuery(nativeQuery, PurchaseRatingCountShopDTO.class).getResultList();
    }

}


