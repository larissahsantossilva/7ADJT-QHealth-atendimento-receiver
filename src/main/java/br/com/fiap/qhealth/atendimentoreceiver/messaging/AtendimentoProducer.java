package br.com.fiap.qhealth.atendimentoreceiver.messaging;

import br.com.fiap.qhealth.atendimentoreceiver.configuration.RabbitMQConfig;
import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtendimentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(AtendimentoEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }

}
