package dev.plotnikov.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectroTypeEmployeePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long employeeId;

    private Long electroTypeId;
}
