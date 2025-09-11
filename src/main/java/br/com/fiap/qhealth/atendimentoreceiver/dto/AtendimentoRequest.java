package br.com.fiap.qhealth.atendimentoreceiver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para novo atendimento")
public class AtendimentoRequest {

    @NotNull(message = "Id do paciente não pode ser nulo.")
    @Schema(description = "Id do paciente", requiredMode = REQUIRED)
    private UUID pacienteId;

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
