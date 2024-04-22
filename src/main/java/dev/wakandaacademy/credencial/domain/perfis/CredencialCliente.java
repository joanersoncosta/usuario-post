package dev.wakandaacademy.credencial.domain.perfis;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.wakandaacademy.credencial.domain.Perfil;
import dev.wakandaacademy.credencial.domain.enuns.TipoPerfil;
import dev.wakandaacademy.usuario.application.api.request.UsuarioNovoRequest;
import lombok.Getter;

@Getter
public class CredencialCliente {
	@NotBlank
	@Email
	private String email;
	@Size(min = 6, max = 9, message = "A senha deve ter no mínimo 6 e no máximo 9 caracteres ")
	private String senha;
	private Perfil perfil;
	
	public CredencialCliente(UsuarioNovoRequest usuario) {
		this.email = usuario.email();
		this.senha = usuario.senha();
		this.perfil = new Perfil(TipoPerfil.USUARIO.name());
	}
}
