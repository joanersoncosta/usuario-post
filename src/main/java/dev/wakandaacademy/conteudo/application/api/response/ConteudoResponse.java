package dev.wakandaacademy.conteudo.application.api.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.conteudo.domain.Conteudo;
import lombok.Value;

@Value
public class ConteudoResponse {
	private UUID idConteudo;
	private UUID idUsuario;
	private String descricao;
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
