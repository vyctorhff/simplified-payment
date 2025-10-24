package br.com.challenge.payment.core.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("tb_account")
@Data
public class Account {

    private Integer id;

    private String email;
    private String password;
}
