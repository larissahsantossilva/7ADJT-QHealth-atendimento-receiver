package br.com.fiap.qhealth.atendimentoreceiver.service;

import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoRequest;
import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;

import java.util.List;
import java.util.UUID;

public interface AtendimentoService {

    UUID iniciar(AtendimentoRequest request);

    List<Atendimento> getAll();

}
