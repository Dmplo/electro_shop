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
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_counter")
    @TableGenerator(name = "employee_counter", pkColumnName = "name", table = "counter", pkColumnValue = "dev.plotnikov.employee.entity.Employee", valueColumnName = "currentid", allocationSize = 2)
    @Column(name = "id_", unique = true, nullable = false)
    @NotNull(message = "Поле employee.id не должно быть пустым")
    private Long id;

    @Column(name = "lastname", nullable = false, length = 100)
    @NotBlank(message = "Поле Фамилия не должно быть пустым")
    @Size(max = 100, message = "Фамилия должна быть не более {max} символов")
    private String lastName;

    @Column(name = "firstname", nullable = false, length = 100)
    @NotBlank(message = "Поле Имя не должно быть пустым")
    @Size(max = 100, message = "Имя должно быть не более {max} символов")
    private String firstName;

    @Column(name = "patronymic", nullable = false, length = 100)
    @NotBlank(message = "Поле Отчество не должно быть пустым")
    @Size(max = 100, message = "Отчество должна быть не более {max} символов")
    private String patronymic;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "position_id", nullable = false)
    @NotNull(message = "Поле employee.position_id не должно быть пустым")
    private Long positionId;

    @Column(name = "shop_id", nullable = false)
    @NotNull(message = "Поле employee.shop_id не должно быть пустым")
    private Long shopId;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Поле Пол не должно быть пустым")
    private Boolean gender;

}