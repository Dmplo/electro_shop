package dev.plotnikov.rest.entity.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@SqlResultSetMapping(
        name = "PurchaseAllMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "purchase_id", type = Long.class),
                                @ColumnResult(name = "purchase_date", type = Object.class),
                                @ColumnResult(name = "amount", type = Long.class),
                                @ColumnResult(name = "purchase_type_name", type = String.class),
                                @ColumnResult(name = "product_id", type = Long.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "product_type_id", type = Long.class),
                                @ColumnResult(name = "product_type_name", type = String.class),
                                @ColumnResult(name = "shop_id", type = Long.class),
                                @ColumnResult(name = "shop_name", type = String.class),
                                @ColumnResult(name = "shop_address", type = String.class),
                                @ColumnResult(name = "employee_id", type = Long.class),
                                @ColumnResult(name = "employee_name", type = String.class),
                                @ColumnResult(name = "position_name", type = String.class)
                        },
                        targetClass = PurchaseAllDTO.class
                )
        }
)

public class PurchaseAllDTO {

    private Long purchaseId;
    private String purchaseDate;
    private Long amount;
    private String purchaseTypeName;
    private Long productId;
    private String productName;
    private Long productTypeId;
    private String productTypeName;
    private Long shopId;
    private String shopName;
    private String shopAddress;
    private Long employeeId;
    private String employeeName;
    private String positionName;

    public PurchaseAllDTO(Long purchaseId, Object purchaseDate, Long amount, String purchaseTypeName, Long productId, String productName, Long productTypeId, String productTypeName, Long shopId, String shopName, String shopAddress, Long employeeId, String employeeName, String positionName) {
        this.purchaseId = purchaseId;
        try {
            Timestamp convDate = (Timestamp) purchaseDate;
            LocalDate purchaseDateTemp = convDate.toLocalDateTime().toLocalDate();
            this.purchaseDate = purchaseDateTemp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            this.purchaseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        this.amount = amount;
        this.purchaseTypeName = purchaseTypeName;
        this.productId = productId;
        this.productName = productName;
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.positionName = positionName;
    }
}


