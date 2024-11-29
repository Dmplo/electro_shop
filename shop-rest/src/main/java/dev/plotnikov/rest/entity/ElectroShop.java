package dev.plotnikov.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ElectroShopPK.class)
@Table(name = "electro_shop")
public class ElectroShop {

    @Id
    @Column(name = "shop_id", nullable = false)
    @NotNull(message = "Поле electro_shop.shop_id не должно быть пустым")
    private Long shopId;

    @Id
    @Column(name = "electro_item_id", nullable = false)
    @NotNull(message = "Поле electro_shop.electro_item_id не должно быть пустым")
    private Long electroItemId;

    @Column(name = "count_", nullable = false)
    @NotNull(message = "Поле Количество товара в магазине не должно быть пустым")
    private Integer count;
}

