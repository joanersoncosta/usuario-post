package dev.wakandaacademy.usuario.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class UsuarioInfraRepository implements UsuarioRepository {
	private final UsuarioSpringDBMongoRepository usuarioSpringDBMongoRepository;

	@Override
	public Usuario salva(Usuario usuario) {
		log.info("[start] UsuarioInfraRepository - salva");
		try {
			usuarioSpringDBMongoRepository.save(usuario);
		} catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Usúario já cadastrado.");
		}
		log.info("[finish] UsuarioInfraRepository - salva");
		return usuario;
	}
	
	@Override
	public Usuario buscaUsuarioPorId(UUID idUsuario) {
		log.info("[inicia] UsuarioInfraRepository - buscaUsuarioPorId");
		Usuario usuario = usuarioSpringDBMongoRepository.findById(idUsuario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Usúario não encontrado!"));
		log.info("[finaliza] UsuarioInfraRepository - buscaUsuarioPorId");
		return usuario;
	}

}
