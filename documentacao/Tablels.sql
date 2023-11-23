
DROP SEQUENCE seq_paciente;
DROP SEQUENCE seq_usuario;
DROP SEQUENCE seq_refeicao;
DROP SEQUENCE seq_historico_glicemia;
DROP SEQUENCE seq_alimento;


CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE;

CREATE SEQUENCE seq_paciente
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE;

CREATE SEQUENCE seq_refeicao
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE;

CREATE SEQUENCE seq_historico_glicemia
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE;

CREATE SEQUENCE seq_alimento
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE;


-- 1. Criando a tabela para armazenar dados do paciente
CREATE TABLE tb_paciente
(
    id_paciente                   NUMBER(10)        PRIMARY KEY,
    nm_completo                   VARCHAR(100)      NOT NULL,
    dt_nascimento                 DATE              NOT NULL ,
    relacao_insulina_carboidrato  INT               NOT NULL,
    max_glicemia                  INT               NOT NULL,
    min_glicemia                  INT               NOT NULL
);

-- 2. Criando a tabela para armazenar dados do usuario
CREATE TABLE tb_usuario
(
    id_usuario   NUMBER(10)       PRIMARY KEY,
    email        VARCHAR(100)     UNIQUE NOT NULL,
    senha        VARCHAR(100)     NOT NULL,
    id_paciente                  NUMBER(10)        NOT NULL,
    CONSTRAINT id_paciente FOREIGN KEY (id_paciente) REFERENCES tb_paciente (id_paciente)
);

-- 3. Criando a tabela para armazenar dados da refeição
CREATE TABLE tb_refeicao
(
    id_refeicao         NUMBER(10) PRIMARY KEY,
    qt_total_carbo      NUMBER(10),
    qt_total_insulina   NUMBER(10),
    id_paciente         NUMBER(10) NOT NULL,
    CONSTRAINT id_paciente_refeicao FOREIGN KEY (id_paciente) REFERENCES tb_paciente (id_paciente)
);

-- 4. Criando a tabela para armazenar dados do historico de glicemia
CREATE TABLE tb_historico_glicemia
(
    id_historico_glicemia NUMBER     PRIMARY KEY,
    vl_destro             INT        NOT NULL ,
    id_paciente           NUMBER(10) NOT NULL,
    CONSTRAINT id_paciente_historico FOREIGN KEY (id_paciente) REFERENCES tb_paciente (id_paciente)
);

-- 5. Criando a tabela para armazenar dados dos alimentos
CREATE TABLE tb_alimento
(
    id_alimento     NUMBER(10)    PRIMARY KEY,
    tipo_alimento   VARCHAR(100)  NOT NULL, --arroz branco
    qt_carbo_grama  FLOAT         NOT NULL -- 0,26 carbo
);

-- Inserindo os INSERTS

