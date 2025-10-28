package br.com.challenge.payment.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_transaction")
@Data
public class Transaction {

    @Id
    private Integer id;

    private Double value;

    private String transactionHash;

    private LocalDateTime dtCreate;

    private StatusTransaction status;

    @Column("id_user_source")
    private AggregateReference<User, Integer> userSource;

    @Column("id_user_target")
    private AggregateReference<User, Integer> userTarget;

    @Transient
    private User userSourceModel;

    @Transient
    private User userTargetModel;

    public Transaction(Double value, User source, User target) {
        this.value = value;
        this.dtCreate = LocalDateTime.now();
        this.transactionHash = UUID.randomUUID().toString();

        this.userSource = AggregateReference.to(source.getId());
        this.userTarget = AggregateReference.to(target.getId());

        this.userSourceModel = source;
        this.userTargetModel = target;
    }

    public boolean isStatusPaid() {
        return StatusTransaction.PAID.equals(this.status);
    }

    public void transfer() {
        this.userSourceModel.getWallet().debit(value);
        this.userTargetModel.getWallet().credit(value);
    }

    public boolean isUserSourceHasBalance() {
        return this.userSourceModel.getWallet()
                .isCanTransfer(this.value);
    }

    public boolean isUserSourceNotLojist() {
        return !this.userSourceModel.isUserLojist();
    }
}
