-- Criação do schema
CREATE SCHEMA IF NOT EXISTS atendimento_receiver;

-- Garante que tudo será criado dentro do schema atendimento_receiver
SET search_path TO atendimento_receiver;

-- Criação da extensão para UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabela de atendimento
CREATE TABLE atendimento (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf VARCHAR(11) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);