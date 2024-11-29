package dev.plotnikov.rest.entity.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@SqlResultSetMapping(
        name = "BookMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "section_name", type = String.class),
                                @ColumnResult(name = "table_name", type = String.class),
                                @ColumnResult(name = "count", type = Integer.class)
                        },
                        targetClass = BookDTO.class
                )
        }
)

public class BookDTO {
    private String sectionName;
    private String tableName;
    private Long count;

    public BookDTO(String sectionName, String tableName, Long count) {
        this.sectionName = sectionName.replace("_", " ");
        this.tableName = tableName;
        this.count = count;
    }
}


