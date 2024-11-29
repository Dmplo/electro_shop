package dev.plotnikov.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop")
public class Shop implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "shop_counter")
    @TableGenerator(name = "shop_counter", pkColumnName = "name", table = "counter", pkColumnValue = "dev.plotnikov.guide.entity.Shop", valueColumnName = "currentid", allocationSize = 2)
    @Column(name = "id_", unique = true, nullable = false)
    @NotNull(message = "Поле electro_shop.id не должно быть пустым")
    private Long id;

    @Column(name = "name", nullable = false, length = 250)
    @NotBlank(message = "Поле Название магазина не должно быть пустым")
    @Size(max = 250, message = "Название магазина должно быть не более {max} символов")
    private String name;

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    @NotNull(message = "Поле Адрес магазина не должно быть пустым")
    @NotBlank(message = "Поле Адрес магазина не должно быть пустым")
    private String address;
}
