package dev.wakandaacademy.usuario.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.usuario.application.api.UsuarioAPI;
import dev.wakandaacademy.usuario.application.api.request.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.api.response.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UsuarioRestController implements UsuarioAPI {
	private final UsuarioService usuarioService;

	@Override
	public UsuarioIdResponse cadastraNovoUsuario(UsuarioNovoRequest usuarioRequest) {
		log.info("[inicia] UsuarioRestController - cadastraNovoUsuario");
		UsuarioIdResponse usuarioIdResponse = usuarioService.cadastraNovoUsuario(usuarioRequest);
		log.info("[finaliza] UsuarioRestController - cadastraNovoUsuario");
		return usuarioIdResponse;
	}

}
