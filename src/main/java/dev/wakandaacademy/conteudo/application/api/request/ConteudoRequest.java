package dev.wakandaacademy.conteudo.application.api.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

import io.swagger.v3.oas.annotations.media.Schema;

public record ConteudoRequest(
		@Indexed @NotBlank(message = "Campo idUsuario não pode está vazio.") 
		@Schema(description = "Este é o idUsuario do Usuário")
		UUID idUsuario,
		@NotBlank(message = "Campo descricao não pode está vazio.") @Size(min = 3, max = 250) 
		@Schema(description = "Esta é a descricao do Conteúdo", example = "Viagem de Férias")
		String descricao,
		@NotBlank(message = "Campo categoria não pode estar vazio.")
		@Schema(description = "Esta é a categoria do Conteúdo", example = "VIAGEM")
		String categoria
) {}