package br.com.challenge.payment.core.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("tb_user")
@Data
public class User {

    @Id
    private Integer id;

    private String name;

    private String cpf;

    private String cnpj;

    @MappedCollection(idColumn = "id_user")
    private Wallet wallet;

    public boolean isUserLojist() {
        return StringUtils.isNoneBlank(cnpj);
    }

    public boolean isUserCommon() {
        return StringUtils.isNoneBlank(cpf);
    }
}
