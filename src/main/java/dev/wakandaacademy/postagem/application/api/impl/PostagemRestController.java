package dev.wakandaacademy.postagem.application.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAPI;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.service.PostagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {
	private final PostagemService postagemService;
	private final TokenService tokenService;

	@Override
	public PostagemIdResponse criaPostagem(String token, PostagemRequest postagemRequest) {
		log.info("[inicia] ConteudoRestController - criaPostagem");
		String email = getUsuarioByToken(token);
		PostagemIdResponse postagemIdResponse = postagemService.criaPostagem(email, postagemRequest);
		log.info("[finaliza] ConteudoRestController - criaPostagem");
		return postagemIdResponse;
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
