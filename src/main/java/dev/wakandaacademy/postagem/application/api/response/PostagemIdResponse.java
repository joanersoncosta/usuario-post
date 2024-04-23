package dev.wakandaacademy.postagem.application.api.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public class PostagemIdResponse {
	@Schema(description = "Este é o ID criado do post")
	private UUID idPostagem;
}
