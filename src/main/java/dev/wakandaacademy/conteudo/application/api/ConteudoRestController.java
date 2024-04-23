package dev.wakandaacademy.conteudo.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ConteudoRestController implements ConteudoAPI {

	private final TokenService tokenService;

	@Override
	public ConteudoIdResponse criaConteudo(String token, ConteudoRequest ConteudoRequest) {
		log.info("[inicia] ConteudoRestController - criaConteudo");
		log.info("[finaliza] ConteudoRestController - criaConteudo");
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
