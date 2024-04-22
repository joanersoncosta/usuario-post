package dev.wakandaacademy.usuario.application.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioNovoRequest(
		@NotBlank(message = "Campo nome não pode está vazio.")
		@Schema(description = "Este é o nome do Cliente", example = "Maria dos Santos")
		String nome,
		@NotBlank(message = "Campo cpf não pode está vazio.")
		@Indexed(unique = true)
		@Size(min = 11, max = 11, message = "número do registro de contribuinte individual brasileiro (CPF) inválido")
//		@CPF
		@Schema(description = "Este é o CPF do Cliente", example = "08248475541")
		String cpf,
		@Email
		@NotBlank(message = "Campo email não pode está vazio.")
		@Indexed(unique = true)
		@Schema(description = "Este é o email do Cliente", example = "maria@gmail.com")
		String email,
		@NotBlank(message = "Campo senha não pode está vazio.")
		@Size(min = 6, max = 9)
		@Schema(description = "Esta é a senha do Cliente", example = "123maria")
		String senha,
		@NotNull(message = "Campo sexo não pode ser nulo.")
		@Schema(description = "Este é o sexo do Cliente", example = "FEMININO")
		String sexo	
		) {
}
