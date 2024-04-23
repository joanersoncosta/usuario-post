package dev.wakandaacademy.conteudo.application.api.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public class ConteudoIdResponse {
	@Schema(description = "Este é o ID criado do conteudo")
	private UUID idConteudo;
}
