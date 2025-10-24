package br.com.challenge.payment.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tb_wallet")
@Data
public class Wallet {

    @Id
    private Integer id;

    private Double currentValue;
}
