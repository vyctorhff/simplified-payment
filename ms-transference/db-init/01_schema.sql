CREATE TABLE tb_user(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(20) NOT NULL,
    cpf VARCHAR(11),
    cnpj VARCHAR(14),

    UNIQUE (cpf),
    UNIQUE (cnpj)
);
CREATE TABLE tb_wallet(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_user INTEGER NOT NULL,
    current_value DOUBLE NOT NULL,

    FOREIGN KEY (id_user) REFERENCES tb_user (id)
);
CREATE TABLE tb_transaction(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_user_source INTEGER NOT NULL,
    id_user_target INTEGER NOT NULL,
    value DOUBLE NOT NULL,
    status VARCHAR(40) NOT NULL,
    transaction_hash VARCHAR(50) NOT NULL,
    dt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (id_user_source) REFERENCES tb_user (id),
    FOREIGN KEY (id_user_target) REFERENCES tb_user (id)
);