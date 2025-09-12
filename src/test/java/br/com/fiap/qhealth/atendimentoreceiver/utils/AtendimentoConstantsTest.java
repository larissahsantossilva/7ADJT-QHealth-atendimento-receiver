package br.com.fiap.qhealth.atendimentoreceiver.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtendimentoConstantsTest {

    @Test
    void testConstantsValues() {
        assertEquals("201", AtendimentoConstants.HTTP_STATUS_CODE_201);
        assertEquals("Fila de atendimento criado com sucesso.", AtendimentoConstants.ATENDIMENTO_CRIADO_COM_SUCESSO);
        assertEquals("Erro ao criar inicio do atendimento.", AtendimentoConstants.ERRO_AO_CRIAR_ATENDIMENTO);
        assertEquals("422", AtendimentoConstants.HTTP_STATUS_CODE_422);
        assertEquals("OK", AtendimentoConstants.OK);
        assertEquals("200", AtendimentoConstants.HTTP_STATUS_CODE_200);
        assertEquals("Fila se encontra vazia", AtendimentoConstants.FILA_ATENDIMENTO_VAZIA);
        assertEquals("204", AtendimentoConstants.HTTP_STATUS_CODE_204);
    }

    @Test
    void testConstructor() {
        AtendimentoConstants constants = new AtendimentoConstants();
        assertNotNull(constants);
    }
}

