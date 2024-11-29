package dev.plotnikov.rest.entity.DTO.ElectroShopCount;

import org.hibernate.transform.ResultTransformer;

import java.util.*;

import static org.aspectj.runtime.internal.Conversions.longValue;

public class ElectroItemDTOResultTransformer implements ResultTransformer {
    private Map<Long, ElectroItemDTO> electroItemTypeDTOMap = new LinkedHashMap<>();

    @Override
    public Object transformTuple(
            Object[] tuple,
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Long itemId = longValue(tuple[aliasToIndexMap.get(ElectroItemDTO.ID_ALIAS)]);

        ElectroItemDTO electroItemDTO = electroItemTypeDTOMap.computeIfAbsent(
                itemId,
                id -> new ElectroItemDTO(tuple, aliasToIndexMap)
        );

        electroItemDTO.getShops().add(
                new ShopDTO(tuple, aliasToIndexMap)
        );
        return electroItemDTO;
    }

    @Override
    public List transformList(List collection) {
        return new ArrayList<>(electroItemTypeDTOMap.values());
    }


    public  Map<String, Integer> aliasToIndexMap(
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }

        return aliasToIndexMap;
    }
}
