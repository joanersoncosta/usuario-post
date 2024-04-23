package dev.wakandaacademy.conteudo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoAlteracaoRequest;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.domain.enuns.CategoriaConteudo;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.handler.APIException;
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
	@NotBlank(message = "Campo categoria não pode estar vazio.")
	private CategoriaConteudo categoria;
	private StatusRestritoConteudo status;
	private int quantidadePostagem;
	private LocalDateTime dataDaCriacao;
	private LocalDateTime dataDaUltimaAlteracao;

	public Conteudo(ConteudoRequest conteudoRequest) {
		this.idConteudo = UUID.randomUUID();
		this.idUsuario = conteudoRequest.idUsuario();
		this.descricao = conteudoRequest.descricao();
		this.categoria = retornaCategoria(conteudoRequest.categoria());
		this.quantidadePostagem = 0;
		this.status = StatusRestritoConteudo.INAVITO;
		this.dataDaCriacao = LocalDateTime.now();
	}

	private CategoriaConteudo retornaCategoria(String categoria) {
		return CategoriaConteudo.validaCategoria(categoria)
	            .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Categória não encontrada."));
	}
	
	public void incrementaQuantidadePostagem() {
		this.quantidadePostagem ++;
	}
	
	public void reduzQuantidadePostagem() {
		this.quantidadePostagem --;
	}

	public void pertenceAoUsuario(Usuario usuarioEmail) {
		if (!this.idUsuario.equals(usuarioEmail.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}		
	}

	public void editaConteudo(ConteudoAlteracaoRequest conteudoRequest) {
		this.descricao = conteudoRequest.descricao();
	}
}
