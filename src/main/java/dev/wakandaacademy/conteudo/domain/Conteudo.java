package dev.wakandaacademy.conteudo.domain;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.conteudo.domain.enuns.StatusConteudo;
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
	private StatusConteudo status;

}
