package dev.wakandaacademy.postagem.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
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
	private UUID idUsuario;
	private String publicador;
	@NotBlank
	@Size
	private String descricao;
	private StatusAtivacaoPostagem statusAtivacao;
	private int quantidadeComentarios;
	private int like;
	private int deslike;
	private Set<PostagemUsuarioLike> likes;
	private Set<PostagemUsuarioDeslike> deslikes;
	private LocalDateTime dataDaCriacao;
	private LocalDateTime dataDaUltimaAlteracao;
}
