package dev.wakandaacademy.usuario.application.api.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsuarioIdResponse {
	@Schema(description = "Este é o ID criado do Cliente")
	private UUID idUsuario;
}
