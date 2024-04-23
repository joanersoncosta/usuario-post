package dev.wakandaacademy.conteudo.application.service.impl;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.service.ConteudoService;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConteudoApplicationService implements ConteudoService {
	private final UsuarioRepository usuarioRepository;

	@Override
	public ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest) {
		log.info("[inicia] ConteudoApplicationService - criaConteudo");
		log.info("[finaliza] ConteudoApplicationService - criaConteudo");
		return null;
	}

}
