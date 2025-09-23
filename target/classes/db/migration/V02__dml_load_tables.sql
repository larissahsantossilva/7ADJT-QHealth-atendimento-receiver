SET search_path TO atendimento_receiver;

-- Populando tabela de especialidade
INSERT INTO atendimento (paciente_id, data_criacao)
VALUES  (uuid_generate_v4(), CURRENT_TIMESTAMP),
        (uuid_generate_v4(), CURRENT_TIMESTAMP),
        (uuid_generate_v4(), CURRENT_TIMESTAMP),
        (uuid_generate_v4(), CURRENT_TIMESTAMP);