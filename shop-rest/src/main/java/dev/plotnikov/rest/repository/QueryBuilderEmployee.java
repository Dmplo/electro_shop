package dev.plotnikov.rest.repository;

import org.springframework.stereotype.Component;

@Component
public class QueryBuilderEmployee {

    public String buildQuery(boolean value) {
        StringBuilder builder = new StringBuilder();

        builder.append("""
                select
                        e.id_ as e_id,
                        e.lastname as e_lastname,
                        e.firstname as e_firstname,
                        e.patronymic as e_patronymic,
                        e.gender as e_gender,
                        p.id_ as p_id,
                        p.name as p_name,
                        sh.id_ as sh_id,
                        sh.name as sh_name,
                        sh.address as sh_address
                        from employee e
                        join position_type p on e.position_id = p.id_
                        join shop sh on e.shop_id = sh.id_
                        """).append(System.lineSeparator());


        if (value) {
            builder.append("where e.id_ = ?1").append(System.lineSeparator());
        }

        builder.append("order by e.lastname, e.firstname, e.patronymic");
        return builder.toString();
    }
}


