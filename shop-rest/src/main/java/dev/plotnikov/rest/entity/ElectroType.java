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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "electro_type")
public class ElectroType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "electro_type_counter")
    @TableGenerator(name = "electro_type_counter", pkColumnName = "name", table = "counter", pkColumnValue = "dev.plotnikov.guide.entity.ElectroType", valueColumnName = "currentid", allocationSize = 2)
    @Column(name = "id_", unique = true, nullable = false)
    @NotNull(message = "Поле electro_type.id не должно быть пустым")
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotBlank(message = "Поле Тип товара не должно быть пустым")
    @Size(max = 150, message = "Тип товара должен быть не более {max} символов")
    private String name;
}
