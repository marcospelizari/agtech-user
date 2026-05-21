CREATE TABLE tb_alertas (
                            id          NUMBER(19,0)  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            usuario_id  NUMBER(10,0)  NOT NULL REFERENCES tb_users(id),
                            consumo_id  NUMBER(19,0)  REFERENCES tb_consumo_agua(id),
                            mensagem    VARCHAR2(500) NOT NULL,
                            data_hora   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
                            resolvido   NUMBER(1,0)   DEFAULT 0 NOT NULL
);