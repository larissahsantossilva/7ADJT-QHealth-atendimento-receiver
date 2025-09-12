package br.com.fiap.qhealth.atendimentoreceiver.controller;

import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoRequest;
import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;
import br.com.fiap.qhealth.atendimentoreceiver.service.AtendimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendimentoControllerTest {

    @Mock
    private AtendimentoService atendimentoService;

    @InjectMocks
    private AtendimentoController atendimentoController;

    private AtendimentoRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        request = new AtendimentoRequest();
        request.setPacienteId(UUID.randomUUID());
        request.setFumante(false);
        request.setGravida(false);
        request.setDiabetico(false);
        request.setHipertenso(false);
    }

    @Test
    void deveCriarAtendimentoComSucesso() {
        UUID atendimentoId = UUID.randomUUID();
        when(atendimentoService.iniciar(request)).thenReturn(atendimentoId);

        ResponseEntity<UUID> response = atendimentoController.criar(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(atendimentoId, response.getBody());
        verify(atendimentoService, times(1)).iniciar(request);
    }

    @Test
    void deveRetornarListaDeAtendimentos() {
        Atendimento atendimento = Atendimento.builder()
                .id(UUID.randomUUID())
                .pacienteId(UUID.randomUUID())
                .dataCriacao(LocalDateTime.now())
                .build();

        when(atendimentoService.getAll()).thenReturn(List.of(atendimento));

        ResponseEntity<List<Atendimento>> response = atendimentoController.buscarTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(atendimentoService, times(1)).getAll();
    }

    @Test
    void deveRetornarNoContentQuandoNaoExistirAtendimentos() {
        when(atendimentoService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Atendimento>> response = atendimentoController.buscarTodos();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(atendimentoService, times(1)).getAll();
    }
}
