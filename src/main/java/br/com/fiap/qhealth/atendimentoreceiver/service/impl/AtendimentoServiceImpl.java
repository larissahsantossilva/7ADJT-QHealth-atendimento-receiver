package br.com.fiap.qhealth.atendimentoreceiver.service.impl;

import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoEvent;
import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoRequest;
import br.com.fiap.qhealth.atendimentoreceiver.messaging.AtendimentoProducer;
import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;
import br.com.fiap.qhealth.atendimentoreceiver.repository.AtendimentoRepository;
import br.com.fiap.qhealth.atendimentoreceiver.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    private final AtendimentoProducer atendimentoProducer;


    @Override
    public UUID iniciar(AtendimentoRequest request) {
        try {
            Atendimento atendimento = Atendimento.builder()
                    .pacienteId(request.getPacienteId())
                    .dataCriacao(LocalDateTime.now())
                    .build();

            Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);

            AtendimentoEvent event = AtendimentoEvent.builder()
                    .id(atendimentoSalvo.getId())
                    .pacienteId(request.getPacienteId())
                    .fumante(request.isFumante())
                    .gravida(request.isGravida())
                    .diabetico(request.isDiabetico())
                    .hipertenso(request.isHipertenso())
                    .dataCriacao(atendimentoSalvo.getDataCriacao())
                    .build();

            atendimentoProducer.send(event);

            return atendimentoSalvo.getId();

        } catch (Exception e) {
            log.error("Erro ao entrar na fila de atendimento para o paciente {}: {}", request.getPacienteId(), e.getMessage(), e);
            throw new RuntimeException("Não foi possível entrar na fila de atendimento. Tente novamente mais tarde.");
        }
    }

    @Override
    public List<Atendimento> getAll() {
        try {
            return atendimentoRepository.findAll();
        } catch (Exception e) {
            log.error("Erro ao buscar fila: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao buscar fila.");
        }
    }

}

