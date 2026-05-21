CREATE TABLE tb_consumo_agua (
                                 id                    NUMBER(19,0)  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                 usuario_id            NUMBER(10,0)  NOT NULL REFERENCES tb_users(id),
                                 volume_litros         NUMBER(10,2)  NOT NULL,
                                 local_monitorado      VARCHAR2(200) NOT NULL,
                                 data_hora             TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                 limite_alerta_litros  NUMBER(10,2)  NOT NULL
);