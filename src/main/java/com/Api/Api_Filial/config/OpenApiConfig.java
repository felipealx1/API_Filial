package com.Api.Api_Filial.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("API Filial")
                        .description("Uma API para o cadastro e controle de Filiais, Departamentos,e Funcionários. A API gerencia um sitema de cadastro de Filiais, Departamentos e Funcionários, quando" +
                                " o Funcionário for cadastrado ele ficará registrado na Filial e no Departamento que irá trabalhar. No cadastro utilizamos uma API Externa (BrasilAPI - CEP) para que quando "+
                                "for feito o cadastro o Funcionário e ele informar o CEP e a API Externa vai fornecer seu endereço completo onde ele reside. Um dos objetivo dessa API é permitir que operações CRUD - com o " +
                                "uso dos métodos HTTP (GET, POST, PUT e DELETE) sejam realizadas e consumir uma API Externa. A API estará conectada ao banco de dados MySQL.")
                        .contact(new Contact().name("José Felipe Alexandre Martins").email("josefelipealexandre1997@gmail.com").url("https://github.com/felipealx1/API_Filial"))
                        .version("Versão 0.0.1-SNAPSHOT"));
    }
}
