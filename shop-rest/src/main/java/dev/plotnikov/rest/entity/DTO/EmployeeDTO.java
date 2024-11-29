package dev.plotnikov.rest.entity.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.booleanValue;
import static org.aspectj.runtime.internal.Conversions.longValue;

@Getter
@Setter
@SqlResultSetMapping(
        name = "EmployeeMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "e_id", type = Long.class),
                                @ColumnResult(name = "e_lastname", type = String.class),
                                @ColumnResult(name = "e_firstname", type = String.class),
                                @ColumnResult(name = "e_patronymic", type = String.class),
                                @ColumnResult(name = "e_gender", type = Boolean.class),
                                @ColumnResult(name = "p_id", type = Long.class),
                                @ColumnResult(name = "p_name", type = String.class),
                                @ColumnResult(name = "sh_id", type = Long.class),
                                @ColumnResult(name = "sh_name", type = String.class),
                                @ColumnResult(name = "sh_address", type = String.class)
                        },
                        targetClass = EmployeeDTO.class
                )
        }
)

public class EmployeeDTO {

    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String gender;
    private Long positionId;
    private String positionName;
    private Long shopId;
    private String shopName;
    private String shopAddress;


    public EmployeeDTO(Long id, String lastName, String firstName, String patronymic, Boolean gender, Long positionId, String positionName, Long shopId, String shopName, String shopAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.gender = gender ? "М" : "Ж";
        this.positionId = positionId;
        this.positionName = positionName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
    }

}