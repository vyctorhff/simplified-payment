package br.com.challenge.payment.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("tb_wallet")
@Data
@NoArgsConstructor
public class Wallet {

    @Id
    private Integer id;

    private Double currentValue;

    public Wallet(Double currentValue) {
        this.currentValue = currentValue;
    }

    public void debit(Double value) {
        this.currentValue = BigDecimal.valueOf(value)
                .subtract(getCurrentValueAsBigDecimal())
                .doubleValue();
    }

    public void credit(Double value) {
        this.currentValue = BigDecimal.valueOf(value)
                .add(getCurrentValueAsBigDecimal())
                .doubleValue();
    }

    public BigDecimal getCurrentValueAsBigDecimal() {
        return BigDecimal.valueOf(currentValue);
    }

    public boolean isCanTransfer(Double value) {
        final int negative = -1;
        int signum = getCurrentValueAsBigDecimal()
                .subtract(BigDecimal.valueOf(value))
                .signum();
        return signum != negative;
    }
}
