package dev.plotnikov.rest.controller.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestParams {

    private List<Integer> year;
    private List<Integer> type;
    private List<Integer> shop;
    private List<Integer> position;
    private List<Integer> pay;
    private String report;

}
