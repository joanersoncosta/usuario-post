package dev.wakandaacademy.comentario.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.usuario.domain.Usuario;
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

	public Comentario(ComentarioRequest postagemRequest, UUID idPublicador, Usuario usuarioEmail) {
		this.idComentario = UUID.randomUUID();
		this.idPostagem = postagemRequest.idPostagem();
		this.idConteudo = postagemRequest.idConteudo();
		this.idPublicador = idPublicador;
		this.idComentarista = usuarioEmail.getIdUsuario();
		this.descricao = postagemRequest.descricao();
		this.status = StatusRestritoConteudo.INAVITO;
		this.like = 0;
		this.deslike = 0;
		this.likes = new HashSet<>();
		this.deslikes = new HashSet<>();
		this.dataDaCriacao = LocalDateTime.now();
	}
}
