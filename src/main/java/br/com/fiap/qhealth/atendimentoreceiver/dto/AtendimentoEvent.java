package br.com.fiap.qhealth.atendimentoreceiver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoEvent {
    private UUID id;
    private String cpf;
    private boolean fumante;
    private boolean gravida;
    private boolean diabetico;
    private boolean hipertenso;
    private LocalDateTime dataCriacao;
}
