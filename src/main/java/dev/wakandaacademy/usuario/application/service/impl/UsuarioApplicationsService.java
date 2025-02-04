package dev.wakandaacademy.usuario.application.service.impl;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.credencial.application.service.CredencialService;
import dev.wakandaacademy.credencial.domain.perfis.CredencialUsuario;
import dev.wakandaacademy.usuario.application.api.request.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.api.response.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.application.service.UsuarioService;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UsuarioApplicationsService implements UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final CredencialService credencialService;

	@Override
	public UsuarioIdResponse cadastraNovoUsuario(UsuarioNovoRequest usuarioRequest) {
		log.info("[inicia] UsuarioApplicationsService - cadastraNovoUsuario");
		Usuario usuario = usuarioRepository.salva(new Usuario(usuarioRequest));
		credencialService.criaNovaCredencial(new CredencialUsuario(usuarioRequest));
		log.info("[finaliza] UsuarioApplicationsService - cadastraNovoUsuario");
		return UsuarioIdResponse.builder().idUsuario(usuario.getIdUsuario()).build();
	}
	
}
