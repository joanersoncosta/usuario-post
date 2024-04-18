package dev.wakandaacademy.usuario.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Usuario")
public class Usuario {
	
	@Id
	private UUID idUsuario;
	@NotBlank
	private String nome;
	@NotBlank
	@Indexed(unique = true)
	@Size(min = 11, max = 11, message = "número do registro de contribuinte individual brasileiro (CPF) inválido")
//	@CPF
	private String cpf;
	@Email
	@NotBlank
	@Indexed(unique = true)
	private String email;
	@NotNull
	private Sexo sexo;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataHoraDaUltimaAlteracao;

	public Usuario(String nome, String cpf, String email, String sexo) {
		this.idUsuario = UUID.randomUUID();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.sexo = retornaSexoValido(sexo);
		this.dataCadastro = LocalDateTime.now();
	}

	private Sexo retornaSexoValido(String sexo) {
		return Sexo.validaSexo(sexo)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Sexo inválido, digite novamente."));
	}

	public void pertenceAoUsuario(Usuario emailUsuario) {
		if (!this.idUsuario.equals(emailUsuario.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não autorizado.");
		}
	}

}
