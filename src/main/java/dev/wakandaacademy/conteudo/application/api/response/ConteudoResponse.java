package dev.wakandaacademy.conteudo.application.api.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.conteudo.domain.Conteudo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ConteudoResponse {
	@Schema(description = "Este é o ID do conteudo")
	private UUID idConteudo;
	@Schema(description = "Este é o ID do Usuario")
	private UUID idUsuario;
	@Schema(description = "Esta é a descricao do Conteúdo", example = "Viagem de Férias")
	private String descricao;
	@Schema(description = "Esta é a data da criação do Conteúdo")
	private LocalDateTime data;

	public ConteudoResponse(Conteudo conteudo) {
		this.idConteudo = conteudo.getIdConteudo();
		this.idUsuario = conteudo.getIdUsuario();
		this.descricao = conteudo.getDescricao();
		this.data = conteudo.getDataDaCriacao();
	}

	public static ConteudoResponse converte(Conteudo conteudo) {
		return new ConteudoResponse(conteudo);
	}
	
	public static List<ConteudoResponse> converte(List<Conteudo> conteudos) {
		return conteudos.stream()
				.map(ConteudoResponse::new)
				.collect(Collectors.toList());
	}

}
