package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.DTO.EmployeeDTO;
import dev.plotnikov.rest.entity.DTO.PurchaseRatingAmountShopDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class EmployeeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final QueryBuilderEmployee builder;

    public EmployeeDTO findById(Long id) {
        String nativeQuery = builder.buildQuery(true);

        try {
           return (EmployeeDTO) entityManager.createNativeQuery(nativeQuery, EmployeeDTO.class).setParameter(1, id).getSingleResult();
        } catch (Exception e) {
            throw new NoSuchElementException("Сотрудник с id = %d не найден".formatted(id));
        }
    }

    public List<EmployeeDTO> findAll() {
        String nativeQuery = builder.buildQuery(false);
        return entityManager.createNativeQuery(nativeQuery, EmployeeDTO.class).getResultList();
    }
}



