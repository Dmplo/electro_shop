package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.PurchaseRatingAmountShopDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRatingAmountShopRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QueryBuilderReportRatingShop builder;
    public List<PurchaseRatingAmountShopDTO> findAmountShopRating(RequestParams params) {

        String nativeQuery = builder.buildQuery(params, "amount");
        return entityManager.createNativeQuery(nativeQuery, PurchaseRatingAmountShopDTO.class).getResultList();
    }

}


