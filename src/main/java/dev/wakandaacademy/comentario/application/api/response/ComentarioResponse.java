package dev.wakandaacademy.comentario.application.api.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.comentario.domain.Comentario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ComentarioResponse {
	@Schema(description = "Este é o ID do comentario")
	private UUID idComentario;
	@Schema(description = "Este é o ID do conteudo")
	private UUID idConteudo;
	@Schema(description = "Este é o ID do post")
	private UUID idPostagem;
	@Schema(description = "Este é o ID do publicador")
	private UUID idPublicador;
	@Schema(description = "Este é o ID do comentarista")
	private UUID idComentarista;
	@Schema(description = "Esta é a descricao do Conteúdo", example = "Viagem de Férias")
	private String descricao;
	@Schema(description = "Esta é a quantidade de like do post", example = "0")
	private int like;
	@Schema(description = "Esta é a quantidade de deslike do post", example = "0")
	private int deslike;
	@Schema(description = "Esta é a data da criação do comentario")
	private LocalDateTime dataDaCriacao;
	
	private ComentarioResponse(Comentario comentario) {
		this.idComentario = comentario.getIdComentario();
		this.idPostagem = comentario.getIdPostagem();
		this.idConteudo = comentario.getIdConteudo();
		this.idPublicador = comentario.getIdPublicador();
		this.idComentarista = comentario.getIdComentarista();
		this.descricao = comentario.getDescricao();
		this.like = comentario.getLike();
		this.deslike = comentario.getDeslike();
		this.dataDaCriacao = comentario.getDataDaCriacao();
	}
	
	public static ComentarioResponse converte(Comentario comentario) {
		return new ComentarioResponse(comentario);
	}

	public static List<ComentarioResponse> converte(List<Comentario> comentarios) {
		return comentarios.stream().map(ComentarioResponse::new)
				.collect(Collectors.toList());
	}

}
