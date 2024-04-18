package dev.wakandaacademy.usuario.application.api.response;

import java.util.UUID;

import lombok.Builder;

@Builder
public class UsuarioIdResponse {
//	@Schema(description = "Este é o ID criado do Cliente")
	private UUID idUsuario;
}
