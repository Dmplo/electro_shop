package dev.plotnikov.rest.entity.DTO;

import dev.plotnikov.rest.entity.ElectroItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ElectroItemPageDTO {
    List<ElectroItem> list;
    private int currentPage;
    private int totalPages;
}
