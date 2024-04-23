package dev.wakandaacademy.postagem.application.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAPI;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {
	private final TokenService tokenService;

	@Override
	public PostagemIdResponse criaPostagem(String token, PostagemRequest postagemRequest) {
		log.info("[inicia] ConteudoRestController - criaPostagem");
		log.info("[finaliza] ConteudoRestController - criaPostagem");
		return null;
	}

	private String getUsuarioByToken(String token) {
		log.info("[inicia] ChamadoRestController - getUsuarioByToken");
		log.debug("[token] {}", token);
		String emailUsuario = tokenService.getUsuarioByBearerToken(token)
				.orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[finaliza] ChamadoRestController - getUsuarioByToken");
		return emailUsuario;
	}
}
