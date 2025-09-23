package br.com.fiap.qhealth.atendimentoreceiver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para novo atendimento")
public class AtendimentoRequest {

    @NotNull(message = "CPF do paciente não pode ser nulo.")
    @Schema(description = "CPF do paciente", requiredMode = REQUIRED)
    private String cpf;

    @NotNull(message = "Fumante não pode ser nulo.")
    @Schema(description = "É fumante?", requiredMode = REQUIRED)
    private boolean fumante;

    @NotNull(message = "Grávida não pode ser nulo.")
    @Schema(description = "Está grávida?", requiredMode = REQUIRED)
    private boolean gravida;

    @NotNull(message = "Diabético não pode ser nulo.")
    @Schema(description = "É diabetico/a?", requiredMode = REQUIRED)
    private boolean diabetico;

    @NotNull(message = "Hipertenso não pode ser nulo.")
    @Schema(description = "É hipertenso/a", requiredMode = REQUIRED)
    private boolean hipertenso;
}
