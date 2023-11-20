/*
Renan Vieira de Jesus RM: 551813
Gabrielle da Silva Stanguini RM: 98786
Lucca RM: 98207
Maria Fernanda RM: 98818
Nicolas RM: 98819
*/

-- 1. Criando as Sequências

CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE

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


-- 1. Criando a tabela para armazenar dados do usuario
CREATE TABLE tb_usuario
(
    id_usuario   NUMBER(10)       PRIMARY KEY,
    email        VARCHAR(100)     UNIQUE NOT NULL,
    senha        VARCHAR(100)     NOT NULL
);

-- 2. Criando a tabela para armazenar dados do paciente
CREATE TABLE tb_paciente
(
    id_paciente                   NUMBER(10)        PRIMARY KEY,
    nm_completo                   VARCHAR(100)      NOT NULL,
    dt_nascimento                 DATE              NOT NULL ,
    relacao_insulina_carboidrato  INT               NOT NULL,
    max_glicemia                  INT               NOT NULL,
    min_glicemia                  INT               NOT NULL ,
    id_usuario                    NUMBER(10)        NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id_usuario)
);

-- 3. Criando a tabela para armazenar dados da refeição
CREATE TABLE tb_refeicao
(
    id_refeicao         NUMBER(10) PRIMARY KEY,
    qt_total_carbo      NUMBER(10),
    qt_total_insulina   NUMBER(10),
    id_paciente         NUMBER(10) NOT NULL,
    CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES tb_paciente (id_paciente)
);

-- 4. Criando a tabela para armazenar dados do historico de glicemia
CREATE TABLE tb_historico_glicemia
(
    id_historico_glicemia NUMBER     PRIMARY KEY,
    vl_destro             INT        NOT NULL ,
    id_paciente           NUMBER(10) NOT NULL,
    CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES tb_paciente (id_paciente)
);

-- 5. Criando a tabela para armazenar dados dos alimentos
CREATE TABLE tb_alimento
(
    id_alimento     NUMBER(10)    PRIMARY KEY,
    tipo_alimento   VARCHAR(100)  NOT NULL, --arroz branco
    qt_carbo_grama  FLOAT         NOT NULL -- 0,26 carbo
);

-- Inserindo os INSERTS

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Renan dos Santos', TO_DATE('01-01-2001', 'DD-MM-YYYY'), '47209828487',
        '11987480978', '09201922', 'São Paulo', 'Rua das jabutiqueiras', '132', 'SP', 'Setor B');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Daiane dos Santos', TO_DATE('24-07-2005', 'DD-MM-YYYY'), '27200828487',
        '11934480978', '09201923', 'Santo André', 'Rua das salsichas', '123', 'SP', 'Casa de cima');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Paulo Roberto Gomes', TO_DATE('12-09-1998', 'DD-MM-YYYY'), '47209899987',
        '11911480978', '09201924', 'São Paulo', 'Rua Conceição', '1', 'MG', 'B');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Maria Silva', TO_DATE('21-02-1988', 'DD-MM-YYYY'), '47243428487', '11900480978',
        '09201925', 'São Caetano', 'Rua Sarambé', '978', 'SP', '23B');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Nicolas Gabriel', TO_DATE('27-08-2005', 'DD-MM-YYYY'), '11109828487', '11978481278',
        '19201926', 'Juazeiro', 'Rua das petunias', '222', 'PA', '3C');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Gabrielle Rodrigues', TO_DATE('04-05-2005', 'DD-MM-YYYY'), '25209828487',
        '1194448-0978', '29201927', 'São Paulo', 'Rua dos anjos', '221', 'SP', '13A');

INSERT INTO tb_pessoa_fisica (id_pf, nm_completo, dt_nascimento, cpf, celular, cep, cidade, logradouro, num_logradouro,
                              estado, complemento)
VALUES (seq_pessoa_fisica.NEXTVAL, 'Lucca Robersval', TO_DATE('03-05-2001', 'DD-MM-YYYY'), '99203828487', '11999480978',
        '09201892', 'São Paulo', 'Rua do professor me da 10', '007', 'SP', '3B');


-- 6. Inserindo dados na tabela Info_Bike
INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Caloi', 'Super V8', 4000, '2020', '0090049012000234000041234000365709190000', 1);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Viking', 'MTB Series', 2000, '2018', '1293923812000234000042224000365709190000', 2);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Caloi', 'Super V8', 4000, '2020', '2133123412000234000011124000365709190000', 3);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Viking', 'Hill Series', 10000, '2017', '154204901200023453225502400036570910000', 4);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Caloi', 'Super V8', 4000, '2020', '7452325112000234000034234000365709190000', 5);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Oggi', 'Panamera', 3500, '2015', '4576765412000234000043234000365709190000', 6);

INSERT INTO tb_info_bike (id_bike, marca, modelo, valor, ano_compra, nota_fiscal, id_pf)
VALUES (seq_info_bike.NEXTVAL, 'Caloi', 'Super V8', 4000, '2020', '7456045712000234000055024000365709190000', 7);


-- 5. Inserindo dados na tabela Acessório
INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '12344321123443211234', 'Vivant', 'CT 49', 'Velocímetro', 500, 1);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '23454321123443211234', 'Caloi', 'PO 15', 'Manômetro', 800, 2);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '34564321123443211234', 'Vivant', 'CT 49', 'Velocímetro', 1234, 3);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '45674321123443211234', 'Vivant', 'Air Hawk', 'Suspensão a Ar', 5000, 4);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '56784321123443211234', 'Vikingx', 'VK2', 'Velocímetro', 1000, 5);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '67894321123443211234', 'Oggi', 'Cloud 1', 'Suspensão Hidráulica', 10000, 6);

INSERT INTO tb_acessorio (id_acessorio, nota_fiscal_acessorio, marca_acessorio, modelo, tipo_acessorio, valor, id_bike)
VALUES (seq_acessorio.NEXTVAL, '78914321123443211234', 'Vivant', 'CT 49', 'Velocímetro', 1000, 7);





-- 8. Atualizando dados na tabela Endereço
UPDATE tb_pessoa_fisica
SET logradouro = 'Rua dos Lins'
WHERE id_pf = 2;

-- 9. Atualizando dados na tabela Acessório
UPDATE tb_acessorio
SET nota_fiscal_acessorio = '12224321123443211234'
WHERE id_acessorio = 3;

-- 10. Atualizando o dado "nota fiscal" na tabela Info_Bike
UPDATE tb_info_bike
SET nota_fiscal = '1111045712000234000055024000365709190000'
WHERE id_bike = 4;

-- 11. Deletando dados na tabela INFO BIKE
DELETE FROM tb_info_bike
WHERE id_bike = 1;

-- 12. Deletando dados na tabela Acessório
DELETE FROM tb_acessorio
WHERE id_acessorio = 5;

-- 13. Deletando dados na tabela Pessoa Física
DELETE FROM tb_acessorio
WHERE id_bike = 4;

-- 14. Criando um relatório usando classificação de dados em ordem descendente na tabela Info_Bike
SELECT marca, modelo, valor, nota_fiscal
FROM tb_info_bike
ORDER BY CAST(SUBSTR(valor, 2) AS NUMBER) DESC;

-- 15. Criando um relatório utilizando uma função numérica simples para calcular a média de valores na tabela Info_Bike
SELECT AVG(CAST(SUBSTR(valor, 0) AS NUMBER)) AS media_valor
FROM tb_info_bike;


-- 16. Criando um relatório para agrupar os tipos de acessórios na tabela Acessórios
SELECT tipo_acessorio, COUNT(*) AS quantidade
FROM tb_acessorio
GROUP BY tipo_acessorio;

-- 17. Criando um relatório que lista o nome completo da pessoa, a cidade onde mora e o valor da bicicleta de pessoas cujo valor seja superior à média
SELECT tb_pessoa_fisica.nm_completo, tb_pessoa_fisica.cidade, tb_info_bike.valor
FROM tb_pessoa_fisica
         JOIN tb_info_bike ON tb_pessoa_fisica.id_pf = tb_info_bike.id_pf
WHERE CAST(SUBSTR(tb_info_bike.valor, 0) AS NUMBER) > (SELECT AVG(CAST(SUBSTR(valor, 0) AS NUMBER)) FROM tb_info_bike);



-- 18. Criando um relatório que lista o nome completo da pessoa, o valor da bicicleta e o tipo de acessório que ela possui
SELECT tb_pessoa_fisica.nm_completo, tb_info_bike.valor, tb_acessorio.tipo_acessorio
FROM tb_pessoa_fisica
         JOIN tb_info_bike ON tb_pessoa_fisica.id_pf = tb_info_bike.id_bike
         JOIN tb_acessorio ON tb_info_bike.id_bike = tb_acessorio.id_acessorio;