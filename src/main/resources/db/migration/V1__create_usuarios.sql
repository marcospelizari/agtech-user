CREATE SEQUENCE SEQ_USERS START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE tb_users (
                          id             NUMBER(10,0)  DEFAULT SEQ_USERS.NEXTVAL PRIMARY KEY,
                          name           VARCHAR2(100) NOT NULL,
                          email          VARCHAR2(150) NOT NULL UNIQUE,
                          password       VARCHAR2(255) NOT NULL,
                          role           VARCHAR2(10)  NOT NULL,
                          date_creation  TIMESTAMP
);