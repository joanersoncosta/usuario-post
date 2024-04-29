package dev.wakandaacademy.postagem.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.request.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
import dev.wakandaacademy.usuario.domain.Usuario;
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
@Document(collection = "Postagem")
@EqualsAndHashCode(of = "idPostagem")
public class Postagem {

	@Id
	private UUID idPostagem;
	private UUID idConteudo;
	@Indexed
	private UUID idPublicador;
	@NotBlank
	@Size
	private String descricao;
	private StatusAtivacaoPostagem statusAtivacao;
	private StatusRestritoConteudo status;
	private int quantidadeComentarios;
	private int like;
	private int deslike;
	private Set<PostagemUsuarioLike> likes;
	private Set<PostagemUsuarioDeslike> deslikes;
	private LocalDateTime dataDaCriacao;
	private LocalDateTime dataDaUltimaAlteracao;

	public Postagem(PostagemRequest postagemRequest, Usuario usuario) {
		this.idPostagem = UUID.randomUUID();
		this.idConteudo = postagemRequest.idConteudo();
		this.idPublicador = usuario.getIdUsuario();
		this.descricao = postagemRequest.descricao();
		this.statusAtivacao = StatusAtivacaoPostagem.INATIVO;
		this.status = StatusRestritoConteudo.INAVITO;
		this.quantidadeComentarios = 0;
		this.like = 0;
		this.deslike = 0;
		this.likes = new HashSet<>();
		this.deslikes = new HashSet<>();
		this.dataDaCriacao = LocalDateTime.now();
	}

	public void pertenceAoConteudo(Conteudo conteudo) {
		if (idConteudo.equals(conteudo.getIdConteudo())) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Post não pertence a este conteúdo.");
		}
	}

	public void pertenceAoUsuario(Usuario emailUsuario) {
		if (!this.idPublicador.equals(emailUsuario.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

	public void alteraPost(EditaPostagemRequest postagemRequest) {
		this.descricao = postagemRequest.descricao();
	}


}
