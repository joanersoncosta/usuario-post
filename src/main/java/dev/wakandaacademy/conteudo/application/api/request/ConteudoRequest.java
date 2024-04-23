package dev.wakandaacademy.conteudo.application.api.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

public record ConteudoRequest(
		@Indexed @NotBlank(message = "Campo idUsuario não pode está vazio.") 
		UUID idUsuario,
		@NotBlank(message = "Campo descricao não pode está vazio.") @Size(min = 3, max = 250) 
		String descricao
) {}