CREATE SEQUENCE SEQ_EVENTS START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE tb_event (
                          id           NUMBER(10,0) DEFAULT SEQ_EVENTS.NEXTVAL PRIMARY KEY,
                          id_user      NUMBER(10,0) REFERENCES tb_users(id),
                          id_address   NUMBER(10,0),
                          type_event   VARCHAR2(50),
                          status       VARCHAR2(30),
                          risk_level   VARCHAR2(30)
);