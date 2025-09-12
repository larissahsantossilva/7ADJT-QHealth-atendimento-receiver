package br.com.fiap.qhealth.atendimentoreceiver.messaging;

import br.com.fiap.qhealth.atendimentoreceiver.configuration.RabbitMQConfig;
import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class AtendimentoProducerTest {

    private RabbitTemplate rabbitTemplate;
    private AtendimentoProducer atendimentoProducer;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        atendimentoProducer = new AtendimentoProducer(rabbitTemplate);
    }

    @Test
    void testSend_shouldCallConvertAndSendWithCorrectParameters() {
        AtendimentoEvent event = new AtendimentoEvent();

        atendimentoProducer.send(event);

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE),
                eq(RabbitMQConfig.ROUTING_KEY),
                eq(event)
        );
    }
}

