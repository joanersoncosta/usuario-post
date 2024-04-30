package dev.wakandaacademy.usuario.application.repository;

import java.util.UUID;

import dev.wakandaacademy.usuario.domain.Usuario;

public interface UsuarioRepository {

	Usuario salva(Usuario usuario);
	
	Usuario buscaUsuarioPorId(UUID idUsuario);
	
	Usuario buscaUsuarioPorEmail(String email);

}
