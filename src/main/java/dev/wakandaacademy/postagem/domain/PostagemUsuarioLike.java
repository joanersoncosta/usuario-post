package dev.wakandaacademy.postagem.domain;

import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import dev.wakandaacademy.postagem.domain.enuns.StatusLikePostagem;
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
public class PostagemUsuarioLike {
	
	@Indexed
	private UUID idUsuario;
	private StatusLikePostagem statusPostagem;
	
	public PostagemUsuarioLike(UUID idUsuario) {
		this.idUsuario = idUsuario;
		this.statusPostagem = StatusLikePostagem.LIKE;
	}
	
}
