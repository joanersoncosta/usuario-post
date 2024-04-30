package dev.wakandaacademy.comentario.application.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public record EditaComentarioRequest(
		@NotBlank(message = "Campo descricao não pode está vazio.") 
		@Size(min = 3, max = 250) @Schema(description = "Esta é a descricao do Conteúdo", example = "Boa viagem, aproveite!") 
		String descricao) {
}
