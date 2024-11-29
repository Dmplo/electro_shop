package dev.plotnikov.rest.entity.DTO.ElectroShopCount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.*;

@Setter
@Getter
@NoArgsConstructor
public class ElectroItemDTO {

    public static final String ID_ALIAS = "p_id";
    public static final String NAME_ALIAS = "p_name";
    public static final String TEXT_ALIAS = "p_text";
    public static final String ARCHIVE_ALIAS = "p_archive";
    public static final String TOTAL_COUNT_ALIAS = "p_count";
    public static final String PRICE_ALIAS = "p_price";
    public static final String TYPE_ID_ALIAS = "t_id";
    public static final String TYPE_NAME_ALIAS = "t_name";

    private Long id;
    private String name;
    private String text;
    private Integer count;
    private Boolean isArchive;
    private Long price;
    private Long typeId;
    private String typeName;

    private List<ShopDTO> shops = new ArrayList<>();

    public ElectroItemDTO(Object[] tuples, Map<String, Integer> aliasToIndexMap) {
        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.name = String.valueOf(tuples[aliasToIndexMap.get(NAME_ALIAS)]);
        this.text = String.valueOf(tuples[aliasToIndexMap.get(TEXT_ALIAS)]);
        this.isArchive = booleanValue(tuples[aliasToIndexMap.get(ARCHIVE_ALIAS)]);
        this.count = intValue(tuples[aliasToIndexMap.get(TOTAL_COUNT_ALIAS)]);
        this.price = longValue(tuples[aliasToIndexMap.get(PRICE_ALIAS)]);
        this.typeId = longValue(tuples[aliasToIndexMap.get(TYPE_ID_ALIAS)]);
        this.typeName = String.valueOf(tuples[aliasToIndexMap.get(TYPE_NAME_ALIAS)]);
    }
}