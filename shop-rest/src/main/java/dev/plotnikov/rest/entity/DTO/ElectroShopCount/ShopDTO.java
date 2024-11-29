package dev.plotnikov.rest.entity.DTO.ElectroShopCount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.intValue;
import static org.aspectj.runtime.internal.Conversions.longValue;

@Setter
@Getter
@NoArgsConstructor
public class ShopDTO {

    public static final String ID_ALIAS = "shop_id";
    public static final String NAME_ALIAS = "shop_name";
    public static final String ADDRESS_ALIAS = "shop_address";
    public static final String COUNT_ALIAS = "shop_count";


    private Long id;
    private String name;
    private String address;
    private Integer count;

    public ShopDTO(Object[] tuples, Map<String, Integer> aliasToIndexMap) {
        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.name = String.valueOf(tuples[aliasToIndexMap.get(NAME_ALIAS)]);
        this.address = String.valueOf(tuples[aliasToIndexMap.get(ADDRESS_ALIAS)]);
        this.count = intValue(tuples[aliasToIndexMap.get(COUNT_ALIAS)]);
    }
}
