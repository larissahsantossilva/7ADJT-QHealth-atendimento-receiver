package br.com.fiap.qhealth.atendimentoreceiver.controller;

import br.com.fiap.qhealth.atendimentoreceiver.dto.AtendimentoRequest;
import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;
import br.com.fiap.qhealth.atendimentoreceiver.service.AtendimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.qhealth.atendimentoreceiver.utils.AtendimentoConstants.*;

@Slf4j
@RestController
@Tag(name = "AtendimentoController", description = "Controller para iniciar atendimento.")
@RequestMapping(value = AtendimentoController.V1_ATENDIMENTOS,
        produces = "application/json")
@RequiredArgsConstructor
public class AtendimentoController {

    static final String V1_ATENDIMENTOS = "/api/v1/atendimentos";

    private final AtendimentoService atendimentoService;

    @Operation(
            description = "Inicia atendimento.",
            summary = "Inicia fila para atendimento.",
            responses = {
                    @ApiResponse(
                            description = ATENDIMENTO_CRIADO_COM_SUCESSO,
                            responseCode = HTTP_STATUS_CODE_201,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = ERRO_AO_CRIAR_ATENDIMENTO,
                            responseCode = HTTP_STATUS_CODE_422,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<UUID> criar(@Valid @RequestBody AtendimentoRequest request) {
        log.info("POST | {} | Iniciando atendimento ", V1_ATENDIMENTOS);
        UUID atendimentoId = atendimentoService.iniciar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoId);
    }

    @Operation(
            description = "Inicia busca de fila de atendimento.",
            summary = "Busca fila de atendimento.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Atendimento.class))
                    ),
                    @ApiResponse(
                            description = FILA_ATENDIMENTO_VAZIA,
                            responseCode = HTTP_STATUS_CODE_204,
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Atendimento>> buscarTodos() {
        log.info("GET | {} | Iniciando busca ", V1_ATENDIMENTOS);
        List<Atendimento> all = atendimentoService.getAll();
        log.info("GET | {} | Finalizando busca ", V1_ATENDIMENTOS);

        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(all);
    }

}

