package dev.plotnikov.rest.entity.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@SqlResultSetMapping(
        name = "PurchaseRatingAmountMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "employee_id", type = Long.class),
                                @ColumnResult(name = "employee_name", type = String.class),
                                @ColumnResult(name = "position_name", type = String.class),
                                @ColumnResult(name = "total_amount", type = BigDecimal.class)
                        },
                        targetClass = PurchaseRatingAmountDTO.class
                )
        }
)

public class PurchaseRatingAmountDTO {
    private Long id;
    private String name;
    private String additionalName;
    private Long total;

    public PurchaseRatingAmountDTO(Long id, String name, String additionalName, BigDecimal total) {
        this.id = id;
        this.name = name;
        this.additionalName = additionalName;
        this.total = total.longValue();
    }

}


