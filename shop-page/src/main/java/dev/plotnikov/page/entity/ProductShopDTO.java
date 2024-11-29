package dev.plotnikov.page.entity;

import java.util.List;

public record ProductShopDTO(Long id,
                             String name,
                             String text,
                             Integer count,
                             Boolean isArchive,
                             Long price,
                             Long typeId,
                             String typeName,
                             List<ShopProductCountDTO>shops) {
}
