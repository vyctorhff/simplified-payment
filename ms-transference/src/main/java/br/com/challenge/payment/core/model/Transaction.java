package br.com.challenge.payment.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_transactional")
@Data
public class Transaction {

    @Id
    private Integer id;

    private Double value;

    private String transactionHash;

    private LocalDateTime dtCreate;

    private StatusTransaction status;

    @MappedCollection(idColumn = "id_user_source")
    private User userSource;

    @MappedCollection(idColumn = "id_user_target")
    private User userTarget;

    public Transaction(Double value, User source, User userTarget) {
        this.value = value;
        this.userSource = source;
        this.userTarget = userTarget;
        this.dtCreate = LocalDateTime.now();
        this.transactionHash = UUID.randomUUID().toString();
    }

    public boolean isStatusPaid() {
        return StatusTransaction.PAID.equals(this.status);
    }

    public Wallet getWalletSource() {
        return this.userSource.getWallet();
    }

    public Wallet getWalletTarget() {
        return this.userTarget.getWallet();
    }

    public void transfer(Double value) {
        this.getWalletSource().debit(value);
        this.getWalletTarget().credit(value);
    }

    public boolean isUserSourceHasBalance() {
        return this.userSource.getWallet()
                .isCanTransfer(this.value);
    }

    public boolean isUserSourceNotLojist() {
        return !this.userSource.isUserLojist();
    }
}
