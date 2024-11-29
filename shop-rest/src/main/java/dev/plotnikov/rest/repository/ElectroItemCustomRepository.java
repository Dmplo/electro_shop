package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.DTO.ElectroShopCount.ElectroItemDTO;
import dev.plotnikov.rest.entity.DTO.ElectroShopCount.ElectroItemDTOResultTransformer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class ElectroItemCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ElectroItemDTO getById(Long id) {
        String query = """ 
                select
                        sh.count_ as shop_count,
                        p.id_ as p_id,
                        p.name as p_name,
                        p.count as p_count,
                        p.archive as p_archive,
                        p.text as p_text,
                        p.price as p_price,
                        t.id_ as t_id,
                        t.name as t_name,
                        sh.shop_id,
                        sh.shop_name,
                        sh.shop_address
                from electro_item p
                        left join (
                            select
                                esh.electro_item_id,
                                esh.count_,
                                sh.id_ as shop_id,
                                sh.name as shop_name,
                                sh.address as shop_address
                            from electro_shop esh
                            join shop sh on esh.shop_id = sh.id_) as sh on sh.electro_item_id = p.id_
                join electro_type t on p.type_id = t.id_
                where p.id_ = ?1
                """;


        try {
            return (ElectroItemDTO) entityManager.createNativeQuery(query).setParameter(1, id).unwrap(org.hibernate.query.Query.class)
                    .setResultTransformer(new ElectroItemDTOResultTransformer()).getSingleResult();
        } catch (Exception e) {
            throw new NoSuchElementException("Товар с id = %d не найден".formatted(id));
        }
    }

}


