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
@IdClass(ElectroTypeEmployeePK.class)
@Table(name = "electro_type_employee")
public class ElectroTypeEmployee {

    @Id
    @Column(name = "employee_id", nullable = false)
    @NotNull(message = "Поле electro_type_employee.employee_id не должно быть пустым")
    private Long employeeId;

    @Id
    @Column(name = "electro_type_id", nullable = false)
    @NotNull(message = "Поле electro_type_employee.electro_type_id не должно быть пустым")
    private Long electroTypeId;
}

