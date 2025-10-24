-- ----------------------------------------------------------------
-- select * from tb_user;
-- select * from tb_account;
-- select * from tb_wallet;
-- delete from tb_wallet;
-- delete from tb_account;
-- delete from tb_user;
-- ----------------------------------------------------------------
-- Usuario
INSERT INTO tb_user(name_first, name_last, cpf, cnpj)
VALUES ("user1 name fist", "user1 name last", "00000000001", null);

INSERT INTO tb_user(name_first, name_last, cpf, cnpj)
VALUES ("user2 name fist", "user2 name last", "00000000002", null);

INSERT INTO tb_user(name_first, name_last, cpf, cnpj)
VALUES ("lojist1 name fist", "", null, "00000000000001");
-- ----------------------------------------------------------------
-- Account
SET @password = '$2a$10$nAJ2Cfmr2nT0/vcYaQUD2eX7hZOmYTVp7PzO62ot2/b3WffES8Pf6';

INSERT INTO tb_account(email, password, id_usuario)
VALUES
(
    "user1@payment.com",
    @password,
    (SELECT id FROM tb_usuario WHERE cpf = "00000000001")
);
INSERT INTO tb_account(email, password, id_usuario)
VALUES
(
    "user2@payment.com",
    @password,
    (SELECT id FROM tb_usuario WHERE cpf = "00000000002")
);
INSERT INTO tb_account(email, password, id_usuario)
VALUES
(
    "lojist1@payment.com",
    @password,
    (SELECT id FROM tb_usuario WHERE cnpj = "00000000000001")
);
-- ----------------------------------------------------------------
-- Wallet
INSERT INTO tb_wallet(current_value, id_usuario)
VALUES
(
    100.0,
    (SELECT id FROM tb_usuario WHERE cpf = "00000000001")
);
INSERT INTO tb_wallet(current_value, id_usuario)
VALUES
(
    100.0,
    (SELECT id FROM tb_usuario WHERE cpf = "00000000002")
);
INSERT INTO tb_wallet(current_value, id_usuario)
VALUES
(
    1000.0,
    (SELECT id FROM tb_usuario WHERE cnpj = "00000000000001")
);
