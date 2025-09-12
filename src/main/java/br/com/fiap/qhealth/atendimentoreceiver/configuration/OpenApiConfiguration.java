package br.com.fiap.qhealth.atendimentoreceiver.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI getAtendimentoReceiverAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("AtendimentoReceiver API Turma 7ADJT")
                                .description("Projeto desenvolvido para Hackatlon.")
                                .version("v1.0.0")
                                .license(new License().name("Apache 2.0").url("https://github.com/larissahsantossilva/7ADJT-QHealth-atendimento-receiver"))
                );
    }

}