package dev.wakandaacademy.conteudo.application.api.response;

import java.time.LocalDateTime;
import java.util.UUID;

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

}
