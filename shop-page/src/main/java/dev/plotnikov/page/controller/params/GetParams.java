package dev.plotnikov.page.controller.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetParams {

    private List<Integer> year;
    private List<Integer> type;
    private List<Integer> shop;
    private List<Integer> position;
    private List<Integer> pay;
    private String report;
}
