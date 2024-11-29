package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.PurchaseRatingAmountDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRatingAmountRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QueryBuilderReportRating builder;
    public List<PurchaseRatingAmountDTO> findAmountRating(RequestParams params) {

        String nativeQuery = builder.buildQuery(params, "amount");
        return entityManager.createNativeQuery(nativeQuery, PurchaseRatingAmountDTO.class).getResultList();
    }

}


