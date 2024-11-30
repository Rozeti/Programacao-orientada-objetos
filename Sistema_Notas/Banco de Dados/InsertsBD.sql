-- Inserts no banco de dados, para teste
USE escola;

-- insert na tabela de Curso
INSERT INTO curso (nome, sigla) 
VALUES 
('1º Ano do Ensino Médio', '1AEM'),
('2º Ano do Ensino Médio', '2AEM'),
('3º Ano do Ensino Médio', '3AEM');

-- Insert na tabela departamento
INSERT INTO departamento (denominacao) 
VALUES
('Matemática'),
('Língua Portuguêsa'),
('História'),
('Geografia'),
('Ciências'),
('Educação Física'),
('Artes'),
('Língua Inglês'),
('Química'),
('Física');


-- Insert da tabela disciplina
INSERT INTO disciplina (denominacao, sigla, ementa, departamento_nome)
VALUES
('Álgebra', 'ALG', 'Estudo de equações, sistemas lineares e operações algébricas.', 'Matemática'),
('Literatura Brasileira', 'LBR', 'Análise de obras literárias brasileiras e seus contextos históricos.', 'Língua Portuguêsa'),
('História do Brasil', 'HBR', 'Estudo da formação histórica e política do Brasil desde a colonização.', 'História'),
('Geografia Física', 'GEO', 'Exploração do relevo, clima e hidrografia mundial.', 'Geografia'),
('Biologia Geral', 'BIO', 'Estudo dos seres vivos, células, anatomia e ecologia.', 'Ciências'),
('Educação Física', 'EDF', 'Práticas esportivas e conceitos de saúde física.', 'Educação Física'),
('Artes Plásticas', 'ART', 'Introdução às técnicas de pintura, desenho e escultura.', 'Artes'),
('Inglês Avançado', 'ING', 'Desenvolvimento avançado da gramática, vocabulário e conversação em inglês.', 'Língua Inglês'),
('Química Orgânica', 'QUI', 'Estudo de compostos orgânicos e suas reações químicas.', 'Química'),
('Física Clássica', 'FIS', 'Introdução aos conceitos de movimento, força e energia.', 'Física');


-- Insert da tabela professor
INSERT INTO professor (matricula_professor, nome, formacao, endereco, telefone, departamento_codigo, disciplina_nome, senha)
VALUES 
(106, 'Fernanda Oliveira', 'Licenciatura em Biologia', 'Rua J, 121, São Paulo', '11987654331', 5, 'Biologia Geral', 'professor567'),
(107, 'Pedro Santos', 'Mestre em Educação Física', 'Rua K, 202, São Paulo', '11987654332', 6, 'Educação Física', 'professor890'),
(108, 'Clara Almeida', 'Doutora em Artes', 'Rua L, 303, São Paulo', '11987654333', 7, 'Artes Plásticas', 'professor345'),
(109, 'André Rocha', 'Mestre em Física', 'Rua M, 404, São Paulo', '11987654334', 10, 'Física Clássica', 'professor678'),
(110, 'Sofia Nunes', 'Mestre em História', 'Rua N, 505, São Paulo', '11987654335', 3, 'História do Brasil', 'professor901'),
(111, 'Lucas Lima', 'Mestre em Matemática', 'Rua O, 606, São Paulo', '11987654336', 1, 'Álgebra', 'professor112'),
(112, 'Mariana Castro', 'Doutora em Letras', 'Rua P, 707, São Paulo', '11987654337', 2, 'Literatura Brasileira', 'professor223'),
(113, 'Rafael Silva', 'Doutor em Geografia', 'Rua Q, 808, São Paulo', '11987654338', 4, 'Geografia Física', 'professor334'),
(114, 'Ana Paula Santos', 'Doutora em Química', 'Rua R, 909, São Paulo', '11987654339', 9, 'Química Orgânica', 'professor445'),
(115, 'Gabriel Almeida', 'Mestre em Inglês', 'Rua S, 1010, São Paulo', '11987654340', 8, 'Inglês Avançado', 'professor556');



-- Insert da tabela aluno
INSERT INTO aluno (matricula_aluno, nome, endereco, telefone, filiacao, data_nascimento, curso_codigo, senha)
VALUES
(2023006, 'Gabriela Fernandes', 'Rua O, 606, São Paulo', '11987654336', 'Miguel Fernandes e Clara Fernandes', '2006-01-12', 1, 'senha678'),
(2023007, 'Lucas Ribeiro', 'Rua P, 707, São Paulo', '11987654337', 'Ricardo Ribeiro e Vanessa Ribeiro', '2005-08-25', 2, 'senha901'),
(2023008, 'Fernanda Costa', 'Rua Q, 808, São Paulo', '11987654338', 'José Costa e Maria Costa', '2004-12-15', 3, 'senha234'),
(2023009, 'Rafael Almeida', 'Rua R, 909, São Paulo', '11987654339', 'Antonio Almeida e Juliana Almeida', '2005-07-19', 1, 'senha567'),
(2023010, 'Beatriz Lima', 'Rua S, 1010, São Paulo', '11987654340', 'Paulo Lima e Ana Lima', '2005-03-08', 2, 'senha890');

-- Insert na tabela de nota, com a nota de cada disciplina registrada no banco de dados e para cada aluno registrado
INSERT INTO nota (aluno_cod_aluno, disciplina_codigo, professor_cod_professor, nota_prova_1, nota_prova_2, nota_prova_3, nota_prova_4)
VALUES
-- Gabriela Fernandes (cod_aluno: 1)
(1, 1, 1, 8.5, 9.0, 7.5, 8.0), -- Álgebra
(1, 2, 2, 7.0, 8.0, 6.5, 7.5), -- Literatura Brasileira
(1, 3, 3, 8.0, 7.5, 9.0, 8.5), -- História do Brasil
(1, 4, 4, 6.5, 7.0, 8.0, 7.0), -- Geografia Física
(1, 5, 5, 8.5, 9.0, 8.0, 8.5), -- Biologia Geral
(1, 6, 6, 9.0, 8.5, 9.0, 9.5), -- Educação Física
(1, 7, 7, 7.5, 8.0, 7.0, 7.5), -- Artes Plásticas
(1, 8, 8, 8.0, 9.0, 9.5, 8.5), -- Inglês Avançado
(1, 9, 9, 7.0, 6.5, 7.5, 7.0), -- Química Orgânica
(1, 10, 10, 9.0, 8.5, 9.5, 9.0), -- Física Clássica

-- Lucas Ribeiro (cod_aluno: 2)
(2, 1, 1, 6.5, 7.0, 6.0, 6.5),
(2, 2, 2, 7.0, 7.5, 6.5, 7.0),
(2, 3, 3, 8.0, 7.5, 8.5, 8.0),
(2, 4, 4, 7.5, 8.0, 7.0, 7.5),
(2, 5, 5, 9.0, 8.5, 8.0, 8.5),
(2, 6, 6, 7.0, 6.5, 7.0, 7.5),
(2, 7, 7, 8.5, 9.0, 8.0, 8.5),
(2, 8, 8, 9.0, 8.5, 9.5, 9.0),
(2, 9, 9, 8.0, 7.5, 8.0, 8.5),
(2, 10, 10, 7.5, 6.0, 6.5, 7.0),

-- Fernanda Costa (cod_aluno: 3)
(3, 1, 1, 8.5, 9.0, 8.5, 9.0),
(3, 2, 2, 9.0, 9.5, 8.5, 8.5),
(3, 3, 3, 7.5, 8.0, 7.5, 8.0),
(3, 4, 4, 8.0, 8.5, 8.5, 8.5),
(3, 5, 5, 6.5, 6.0, 7.0, 6.5),
(3, 6, 6, 8.5, 8.0, 8.0, 8.5),
(3, 7, 7, 9.0, 8.5, 9.0, 9.0),
(3, 8, 8, 7.5, 8.0, 7.5, 7.5),
(3, 9, 9, 8.0, 9.0, 8.5, 8.5),
(3, 10, 10, 9.5, 9.0, 9.5, 9.5),

-- Rafael Almeida (cod_aluno: 4)
(4, 1, 1, 7.0, 6.5, 7.0, 6.5),
(4, 2, 2, 6.5, 7.0, 6.5, 7.0),
(4, 3, 3, 8.0, 8.5, 8.0, 8.5),
(4, 4, 4, 7.5, 7.0, 7.5, 7.0),
(4, 5, 5, 8.5, 9.0, 8.0, 8.5),
(4, 6, 6, 7.0, 6.5, 6.5, 7.0),
(4, 7, 7, 8.5, 8.0, 8.5, 8.5),
(4, 8, 8, 9.0, 9.5, 9.0, 9.5),
(4, 9, 9, 7.5, 6.5, 7.0, 6.5),
(4, 10, 10, 6.0, 6.5, 7.0, 6.5),

-- Beatriz Lima (cod_aluno: 5)
(5, 1, 1, 9.5, 9.0, 9.5, 9.0),
(5, 2, 2, 8.0, 7.5, 8.0, 7.5),
(5, 3, 3, 7.5, 8.0, 8.0, 8.0),
(5, 4, 4, 6.5, 6.0, 6.5, 6.0),
(5, 5, 5, 7.0, 7.5, 7.0, 7.5),
(5, 6, 6, 8.5, 8.0, 8.5, 8.0),
(5, 7, 7, 9.0, 9.5, 9.0, 9.5),
(5, 8, 8, 7.0, 6.5, 7.0, 6.5),
(5, 9, 9, 6.5, 7.0, 6.5, 7.0),
(5, 10, 10, 8.0, 8.5, 8.0, 8.5);

