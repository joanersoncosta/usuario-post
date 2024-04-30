package dev.wakandaacademy.comentario.domain;

import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import dev.wakandaacademy.comentario.domain.enuns.StatusLikeComentario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "idUsuario")
public class ComentarioUsuarioDeslike {
	
	@Indexed
	private UUID idUsuario;
	private StatusLikeComentario statusPostagem;
	
	public ComentarioUsuarioDeslike(UUID idUsuario) {
		this.idUsuario = idUsuario;
		this.statusPostagem = StatusLikeComentario.DESLIKE;
	}
	
}
