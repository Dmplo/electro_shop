package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.PurchaseRatingCountDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRatingCountRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QueryBuilderReportRating builder;
    public List<PurchaseRatingCountDTO> findCountRating(RequestParams params) {

        String nativeQuery = builder.buildQuery(params, "count_");
        return entityManager.createNativeQuery(nativeQuery, PurchaseRatingCountDTO.class).getResultList();
    }

}


