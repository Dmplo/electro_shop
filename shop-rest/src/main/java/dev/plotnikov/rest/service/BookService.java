package dev.plotnikov.rest.service;


import dev.plotnikov.rest.entity.DTO.BookDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BookDTO> getCount() {
        String nativeQuery =""" 
                    select 
                        'Должность' as section_name, 
                        'positions' as table_name,
                         count(*) as count  
                    from position_type
                union all
                    select 
                        'Тип_покупки', 'pays',
                         count(*) 
                    from purchase_type
                union all
                     select 
                     'Тип_электроники', 'types',
                     count(*) 
                from electro_type  
                """;

        return entityManager.createNativeQuery(nativeQuery, BookDTO.class).getResultList();
    }

}
