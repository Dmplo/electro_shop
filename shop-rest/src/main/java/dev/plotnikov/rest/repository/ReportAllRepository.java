package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.PurchaseAllPageDTO;
import dev.plotnikov.rest.entity.DTO.PurchaseAllDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportAllRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final PurchaseRepository repository;

    public PurchaseAllPageDTO findAllCustom(RequestParams params, Pageable pageable) {

        Long count = repository.findPurchaseCount();
        long totalCount = Math.max(pageable.getPageSize(), count);
        Page<PurchaseAllDTO> pageableRes = new PageImpl<>(List.of(), pageable, totalCount);
        PurchaseAllPageDTO result = new PurchaseAllPageDTO(null, pageable.getPageNumber(), pageableRes.getTotalPages());

        StringBuilder builder = new StringBuilder();
        StringBuilder whereBuilder = new StringBuilder("WHERE 1 = 1").append(System.lineSeparator());

        builder.append(""" 
                select distinct
                        pur.id_ as purchase_id,
                        pur.purchase_date as purchase_date,
                        pur.amount as amount,
                        put.name as purchase_type_name,
                        p.id_ as product_id,
                        p.name as product_name,
                        pot.id_ as product_type_id,
                        pot.name as product_type_name,
                        sh.id_ as shop_id,
                        sh.name as shop_name,
                        sh.address as shop_address,
                        e.id_ as employee_id,
                        concat(e.lastname, ' ', substring(e.firstname, 1, 1), '.', substring(e. patronymic, 1, 1), '.') as employee_name,
                        pos.name as position_name
                        from purchase pur
                        join electro_item p on pur.electro_id = p.id_
                        join electro_type pot on p.type_id = pot.id_
                        join electro_shop eh on eh.electro_item_id = p.id_
                        join employee e on pur.employee_id = e.id_
                        join position_type pos on e.position_id = pos.id_
                        join purchase_type put on pur.type_id = put.id_
                        join shop sh on pur.shop_id = sh.id_
                """).append(System.lineSeparator());

        if (params.getType() != null && !params.getType().isEmpty()) {
            whereBuilder.append("and pot.id_ in(").append(fillData(params.getType()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getYear() != null && !params.getYear().isEmpty()) {
            whereBuilder.append("and extract(year from purchase_date) in(").append(fillData(params.getYear()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getShop() != null && !params.getShop().isEmpty()) {
            whereBuilder.append("and sh.id_ in(").append(fillData(params.getShop()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getPosition() != null && !params.getPosition().isEmpty()) {
            whereBuilder.append("and pos.id_ in(").append(fillData(params.getPosition()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getPay() != null && !params.getPay().isEmpty()) {
            whereBuilder.append("and put.id_ in(").append(fillData(params.getPay()))
                    .append(")").append(System.lineSeparator());
        }

        whereBuilder.append("order by pur.purchase_date").append(System.lineSeparator());
        builder.append(whereBuilder);
        String nativeQuery = builder.toString();

        if (pageable.getPageNumber() <= pageableRes.getTotalPages()) {
            List<PurchaseAllDTO> resultList = entityManager.createNativeQuery(nativeQuery, PurchaseAllDTO.class).
                    setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                    .setMaxResults(pageable.getPageSize()).getResultList();

            result.setPurchasesDTO(resultList);
        }
        return result;
    }

    private <T> String fillData(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}


