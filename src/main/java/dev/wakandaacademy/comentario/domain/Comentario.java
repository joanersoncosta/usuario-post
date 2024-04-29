package dev.wakandaacademy.comentario.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Comentario")
public class Comentario {

	@Id
	private UUID idComentario;
	@Indexed
	private UUID idPostagem;
	@Indexed
	private UUID idConteudo;
	@Indexed
	private UUID idPublicador;
	@Indexed
	private UUID idComentarista;
	@NotBlank
	@Size
	private String descricao;
	private StatusRestritoConteudo status;
	private int like;
	private int deslike;
	private Set<ComentarioUsuarioLike> likes;
	private Set<ComentarioUsuarioDeslike> deslikes;
	private LocalDateTime dataDaCriacao;
	private LocalDateTime dataDaUltimaAlteracao;

}
