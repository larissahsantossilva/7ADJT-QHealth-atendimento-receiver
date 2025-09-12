package br.com.fiap.qhealth.atendimentoreceiver.service.impl;

import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoEvent;
import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoRequest;
import br.com.fiap.qhealth.atendimentoreceiver.exception.AtendimentoException;
import br.com.fiap.qhealth.atendimentoreceiver.messaging.AtendimentoProducer;
import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;
import br.com.fiap.qhealth.atendimentoreceiver.repository.AtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendimentoServiceImplTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private AtendimentoProducer atendimentoProducer;

    @InjectMocks
    private AtendimentoServiceImpl atendimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveIniciarAtendimentoComSucesso() {
        UUID pacienteId = UUID.randomUUID();
        UUID atendimentoId = UUID.randomUUID();

        AtendimentoRequest request = AtendimentoRequest.builder()
                .pacienteId(pacienteId)
                .fumante(true)
                .gravida(false)
                .diabetico(true)
                .hipertenso(false)
                .build();

        Atendimento atendimentoSalvo = Atendimento.builder()
                .id(atendimentoId)
                .pacienteId(pacienteId)
                .dataCriacao(LocalDateTime.now())
                .build();

        when(atendimentoRepository.save(any(Atendimento.class))).thenReturn(atendimentoSalvo);

        UUID result = atendimentoService.iniciar(request);

        assertNotNull(result);
        assertEquals(atendimentoId, result);
        verify(atendimentoRepository, times(1)).save(any(Atendimento.class));
        verify(atendimentoProducer, times(1)).send(any(AtendimentoEvent.class));
    }

    @Test
    void deveLancarExcecaoAoIniciarAtendimento() {
        AtendimentoRequest request = AtendimentoRequest.builder()
                .pacienteId(UUID.randomUUID())
                .build();

        when(atendimentoRepository.save(any(Atendimento.class)))
                .thenThrow(new RuntimeException("Erro no banco"));

        AtendimentoException ex = assertThrows(AtendimentoException.class, () -> atendimentoService.iniciar(request));
        assertEquals("Não foi possível iniciar o atendimento. Tente novamente mais tarde.", ex.getMessage());

        verify(atendimentoRepository, times(1)).save(any(Atendimento.class));
        verify(atendimentoProducer, never()).send(any());
    }

    @Test
    void deveRetornarTodosAtendimentos() {
        Atendimento atendimento = Atendimento.builder()
                .id(UUID.randomUUID())
                .pacienteId(UUID.randomUUID())
                .dataCriacao(LocalDateTime.now())
                .build();

        when(atendimentoRepository.findAll()).thenReturn(List.of(atendimento));

        List<Atendimento> result = atendimentoService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(atendimentoRepository, times(1)).findAll();
    }

    @Test
    void deveLancarExcecaoAoBuscarTodosAtendimentos() {
        when(atendimentoRepository.findAll()).thenThrow(new RuntimeException("Erro ao buscar"));

        AtendimentoException ex = assertThrows(AtendimentoException.class, () -> atendimentoService.getAll());
        assertEquals("Erro ao buscar fila de atendimentos.", ex.getMessage());

        verify(atendimentoRepository, times(1)).findAll();
    }
}

