CREATE SCHEMA IF NOT EXISTS escola DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE escola;

-- Tabela de Cursos
CREATE TABLE curso (
  codigo INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  sigla VARCHAR(10) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

-- Tabela de Alunos
CREATE TABLE aluno (
  cod_aluno INT NOT NULL AUTO_INCREMENT,
  matricula_aluno INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(200),
  telefone VARCHAR(15),
  filiacao VARCHAR(200),
  data_nascimento DATE NOT NULL,
  curso_codigo INT NOT NULL,
  senha VARCHAR(255) NOT NULL,
  PRIMARY KEY (cod_aluno),
  UNIQUE INDEX matricula_aluno_UNIQUE (matricula_aluno),  -- Garantir matrícula única
  FOREIGN KEY (curso_codigo) REFERENCES curso (codigo)
) ENGINE=InnoDB;

-- Tabela de Departamentos
CREATE TABLE departamento (
  codigo INT NOT NULL AUTO_INCREMENT,
  denominacao VARCHAR(100) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE INDEX idx_denominacao_unique (denominacao) -- Criando índice único para denominação
) ENGINE=InnoDB;

-- Tabela de Professores
CREATE TABLE professor (
  cod_professor INT NOT NULL AUTO_INCREMENT,
  matricula_professor INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  formacao VARCHAR(100),
  endereco VARCHAR(200),
  telefone VARCHAR(15),
  departamento_codigo INT NOT NULL,
  disciplina_nome VARCHAR(100) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  PRIMARY KEY (cod_professor),
  UNIQUE INDEX matricula_professor_UNIQUE (matricula_professor),
  FOREIGN KEY (departamento_codigo) REFERENCES departamento (codigo) -- Agora relacionando pelo código do departamento
) ENGINE=InnoDB;

-- Tabela de Disciplinas
CREATE TABLE disciplina (
  codigo INT NOT NULL AUTO_INCREMENT,
  denominacao VARCHAR(100) NOT NULL,
  sigla VARCHAR(10) NOT NULL,
  ementa TEXT NOT NULL,
  departamento_nome VARCHAR(100) NOT NULL,  -- Alterando para nome do departamento
  PRIMARY KEY (codigo),
  FOREIGN KEY (departamento_nome) REFERENCES departamento (denominacao)  -- Relacionando o departamento pelo nome
) ENGINE=InnoDB;

CREATE TABLE matricula_disciplina (
    aluno_cod_aluno INT NOT NULL,
    disciplina_codigo INT NOT NULL,
    PRIMARY KEY (aluno_cod_aluno, disciplina_codigo),
    FOREIGN KEY (aluno_cod_aluno) REFERENCES aluno(cod_aluno),
    FOREIGN KEY (disciplina_codigo) REFERENCES disciplina(codigo)
);

-- Tabela de Notas (com colunas para as 4 provas)
CREATE TABLE nota (
  aluno_cod_aluno INT NOT NULL,  -- Alterando para usar cod_aluno
  disciplina_codigo INT NOT NULL,
  professor_cod_professor INT NOT NULL,  -- Alterando para usar cod_professor
  nota_prova_1 DECIMAL(5,2) NULL,
  nota_prova_2 DECIMAL(5,2) NULL,
  nota_prova_3 DECIMAL(5,2) NULL,
  nota_prova_4 DECIMAL(5,2) NULL,
  PRIMARY KEY (aluno_cod_aluno, disciplina_codigo),
  FOREIGN KEY (aluno_cod_aluno) REFERENCES aluno (cod_aluno),  -- Alterado para usar cod_aluno
  FOREIGN KEY (disciplina_codigo) REFERENCES disciplina (codigo),
  FOREIGN KEY (professor_cod_professor) REFERENCES professor (cod_professor)  -- Alterado para usar cod_professor
) ENGINE=InnoDB;