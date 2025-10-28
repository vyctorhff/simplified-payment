-- drop table tb_user;
-- drop table tb_account;
-- drop table tb_wallet;
-- ----------------------------------------------------------------

CREATE TABLE tb_user(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_first VARCHAR(40) NOT NULL,
    name_last VARCHAR(40) NOT NULL,
    cpf VARCHAR(11),
    cnpj VARCHAR(14),

    UNIQUE (cpf),
    UNIQUE (cnpj)
);
CREATE TABLE tb_account(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_user INTEGER NOT NULL,
    email VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,

    UNIQUE (email),

    FOREIGN KEY (id_user) REFERENCES tb_user (id)
);
CREATE TABLE tb_wallet(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_user INTEGER NOT NULL,
    current_value DOUBLE NOT NULL,

    FOREIGN KEY (id_user) REFERENCES tb_user (id)
);
