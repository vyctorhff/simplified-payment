package br.com.challenge.payment.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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

    @MappedCollection(idColumn = "id_walllet_source")
    private Wallet walletSource;

    @MappedCollection(idColumn = "id_walllet_target")
    private Wallet walletTarget;

    public Transaction(Double value, User source, User userTarget) {
        this.value = value;
        this.userSource = source;
        this.userTarget = userTarget;
    }

    public boolean isStatusPaid() {
        return StatusTransaction.PAID.equals(this.status);
    }
}
