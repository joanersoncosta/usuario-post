package dev.wakandaacademy.conteudo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
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
@Document(collection = "Conteudo")
public class Conteudo {

	@Id
	private UUID idConteudo;
	@Indexed
	@NotBlank(message = "Campo idUsuario não pode está vazio.")
	private UUID idUsuario;
	@NotBlank(message = "Campo descricao não pode está vazio.")
	@Size(min = 3, max = 250)
	private String descricao;
	private StatusRestritoConteudo status;
	private LocalDateTime dataDaCriacao;
	private LocalDateTime dataDaUltimaAlteracao;

	public Conteudo(ConteudoRequest conteudoRequest) {
		this.idConteudo = UUID.randomUUID();
		this.idUsuario = conteudoRequest.idUsuario();
		this.descricao = conteudoRequest.descricao();
		this.status = StatusRestritoConteudo.INAVITO;
		this.dataDaCriacao = LocalDateTime.now();
	}

}
