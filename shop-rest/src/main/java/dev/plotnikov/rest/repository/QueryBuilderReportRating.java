package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.controller.params.RequestParams;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryBuilderReportRating {

    public String buildQuery(RequestParams params, String columnName) {
        StringBuilder builder = new StringBuilder();

        StringBuilder whereBuilder = new StringBuilder("where 1 = 1").append(System.lineSeparator());

        builder.append(""" 
                select
                    pur.employee_id as employee_id,
                    concat(e.lastname, ' ', substring(e.firstname, 1, 1), '.', substring(e. patronymic, 1, 1), '.') as employee_name,
                    pos.name as position_name,
                    sum(pur.%s) as total_%s
                from purchase pur
                """.formatted(columnName, columnName)).append(System.lineSeparator());

        builder.append("join employee e on pur.employee_id = e.id_")
                .append(System.lineSeparator());
        builder.append("join position_type pos on e.position_id = pos.id_")
                .append(System.lineSeparator());

        if (params.getType() != null && !params.getType().isEmpty()) {
            whereBuilder.append("and pot.id_ in(").append(fillData(params.getType()))
                    .append(")").append(System.lineSeparator());
            builder.append("join electro_item p on pur.electro_id = p.id_")
                    .append(System.lineSeparator());
            builder.append("join electro_type pot on p.type_id = pot.id_")
                    .append(System.lineSeparator());
        }

        if (params.getYear() != null && !params.getYear().isEmpty()) {
            whereBuilder.append("and extract(year from purchase_date) in(").append(fillData(params.getYear()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getShop() != null && !params.getShop().isEmpty()) {
            whereBuilder.append("and sh.id_ in(").append(fillData(params.getShop()))
                    .append(")").append(System.lineSeparator());
            builder.append("join shop sh on pur.shop_id = sh.id_")
                    .append(System.lineSeparator());
        }

        if (params.getPosition() != null && !params.getPosition().isEmpty()) {
            whereBuilder.append("and pos.id_ in(").append(fillData(params.getPosition()))
                    .append(")").append(System.lineSeparator());
        }

        if (params.getPay() != null && !params.getPay().isEmpty()) {
            whereBuilder.append("and put.id_ in(").append(fillData(params.getPay()))
                    .append(")").append(System.lineSeparator());
            builder.append("join purchase_type put on pur.type_id = put.id_")
                    .append(System.lineSeparator());
        }

        whereBuilder.append("group by employee_id, employee_name, position_name").append(System.lineSeparator())
                .append("order by total_%s desc".formatted(columnName)).append(System.lineSeparator());
        builder.append(whereBuilder);
        return builder.toString();
    }

    private <T> String fillData(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}


