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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "electro_item")
public class ElectroItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "electro_item_counter")
    @TableGenerator(name = "electro_item_counter", pkColumnName = "name", table = "counter", pkColumnValue = "dev.plotnikov.product.entity.ElectroItem", valueColumnName = "currentid", allocationSize = 2)
    @Column(name = "id_", unique = true, nullable = false)
    @NotNull(message = "Поле electro_item.id не должно быть пустым")
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotBlank(message = "Поле Наименование товара не должно быть пустым")
    @Size(max = 150, message = "Наименование товара должно быть не более {max} символов")
    private String name;

    @Column(name = "type_id", nullable = false)
    @NotNull(message = "Поле electro_item.type_id не должно быть пустым")
    private Long typeId;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Поле Цена не должно быть пустым")
    private Long price;

    @Column(name = "count", nullable = false)
    @NotNull(message = "Поле Количество не должно быть пустым")
    private Integer count;

    @Column(name = "archive", nullable = false)
    @NotNull(message = "Поле Архив не должно быть пустым")
    private Boolean archive;

    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    @NotNull(message = "Поле Описание не должно быть пустым")
    private String text;


    @Override
    public String toString() {
        return "ElectroItem{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", typeId=" + typeId +
               ", price=" + price +
               ", count=" + count +
               ", archive=" + archive +
               ", text='" + text + '\'' +
               '}';
    }
}
