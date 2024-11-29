package dev.plotnikov.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_counter")
    @TableGenerator(name = "purchase_counter", pkColumnName = "name", pkColumnValue = "dev.plotnikov.purchase.entity.Purchase", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    @NotNull(message = "Поле purchase.id не должно быть пустым")
    private Long id;

    @Column(name = "amount", nullable = false)
    @NotNull(message = "Поле Сумма не должно быть пустым")
    private Long amount;

    @Column(name = "count_", nullable = false)
    @NotNull(message = "Поле Количество не должно быть пустым")
    private Long count;

    @Column(name = "electro_id", nullable = false)
    @NotNull(message = "Поле purchase.electro_id не должно быть пустым")
    private Long electroId;

    @Column(name = "employee_id", nullable = false)
    @NotNull(message = "Поле purchase.employee_id не должно быть пустым")
    private Long employeeId;

    @Column(name = "purchase_date", nullable = false)
    @NotNull(message = "Поле Дата заказа не должно быть пустым")
    private LocalDateTime purchaseDate;

    @Column(name = "type_id", nullable = false)
    @NotNull(message = "Поле purchase.type_id не должно быть пустым")
    private Long typeId;

    @Column(name = "shop_id", nullable = false)
    @NotNull(message = "Поле purchase.shop_id не должно быть пустым")
    private Long shopId;

}




