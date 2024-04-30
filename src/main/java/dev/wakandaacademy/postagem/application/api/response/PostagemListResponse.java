package dev.wakandaacademy.postagem.application.api.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.postagem.domain.Postagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class PostagemListResponse {
	@Schema(description = "Este é o ID do post")
	private UUID idPostagem;
	@Schema(description = "Este é o ID do conteudo")
	private UUID idConteudo;
	@Schema(description = "Este é o ID do publicador")
	private UUID idPublicador;
	@Schema(description = "Esta é a descricao do Conteúdo", example = "Viagem de Férias")
	private String descricao;
	@Schema(description = "Esta é a quantidade de comentários", example = "2")
	private int quantidadeComentarios;
	@Schema(description = "Esta é a quantidade de like do post", example = "2")
	private int like;
	@Schema(description = "Esta é a quantidade de deslike do post", example = "1")
	private int deslike;
	@Schema(description = "Esta é a data da criação do post")
	private LocalDateTime dataDaCriacao;

	private PostagemListResponse(Postagem post) {
		this.idPostagem = post.getIdPostagem();
		this.idConteudo = post.getIdConteudo();
		this.idPublicador = post.getIdPublicador();
		this.descricao = post.getDescricao();
		this.quantidadeComentarios = post.getQuantidadeComentarios();
		this.like = post.getLike();
		this.deslike = post.getDeslike();
		this.dataDaCriacao = post.getDataDaCriacao();
	}

	public static List<PostagemListResponse> converte(List<Postagem> posts) {
		return posts.stream().map(PostagemListResponse::new).collect(Collectors.toList());
	}

}
