package dev.wakandaacademy.comentario.application.api.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

import io.swagger.v3.oas.annotations.media.Schema;

public record ComentarioRequest(
		@Indexed @NotBlank(message = "Campo idUsuario não pode está vazio.") 
		@Schema(description = "Este é o idUsuario do Usuário")
		UUID idUsuario,
		@Indexed @NotBlank(message = "Campo idConteudo não pode está vazio.") 
		@Schema(description = "Este é o idConteudo do Conteúdo")
		UUID idConteudo,
		@Indexed @NotBlank(message = "Campo idConteudo não pode está vazio.") 
		@Schema(description = "Este é o idPostagem do post")
		UUID idPostagem,
		@NotBlank(message = "Campo descricao não pode está vazio.") @Size(min = 3, max = 250) 
		@Schema(description = "Esta é a descricao do comentário", example = "Boa viagem, aproveite!")
		String descricao) {
}
