package dev.wakandaacademy.comentario.application.api.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ComentarioIdResponse {
	@Schema(description = "Este é o ID criado do comentario")
	private UUID idComentario;
}
