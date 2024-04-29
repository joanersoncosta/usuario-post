package dev.wakandaacademy.conteudo.application.api.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.conteudo.application.api.ConteudoAPI;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoAlteracaoRequest;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;
import dev.wakandaacademy.conteudo.application.service.ConteudoService;
import dev.wakandaacademy.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ConteudoRestController implements ConteudoAPI {
	private final ConteudoService conteudoService;
	private final TokenService tokenService;

	@Override
	public ConteudoIdResponse criaConteudo(String token, ConteudoRequest ConteudoRequest) {
		log.info("[inicia] ConteudoRestController - criaConteudo");
		String email = getUsuarioByToken(token);
		ConteudoIdResponse conteudoIdResponse = conteudoService.criaConteudo(email, ConteudoRequest);
		log.info("[finaliza] ConteudoRestController - criaConteudo");
		return conteudoIdResponse;
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

	@Override
	public ConteudoResponse buscaConteudoPorId(String token, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - buscaConteudoPorId");
		String email = getUsuarioByToken(token);
		ConteudoResponse conteudoResponse = conteudoService.buscaConteudoPorId(email, idConteudo);
		log.info("[finaliza] ConteudoRestController - buscaConteudoPorId");
		return conteudoResponse;
	}

	@Override
	public List<ConteudoResponse> buscaTodosOsConteudos(String token) {
		log.info("[inicia] ConteudoRestController - buscaTodosOsConteudos");
		String email = getUsuarioByToken(token);
		List<ConteudoResponse> conteudoResponse = conteudoService.buscaTodosOsConteudos(email);
		log.info("[finaliza] ConteudoRestController - buscaTodosOsConteudos");
		return conteudoResponse;
	}

	@Override
	public List<ConteudoResponse> buscaTodosOsConteudosDoUsuario(String token, UUID idUsuario) {
		log.info("[inicia] ConteudoRestController - buscaTodosOsConteudosDoUsuario");
		String email = getUsuarioByToken(token);
		List<ConteudoResponse> conteudoResponse = conteudoService.buscaTodosOsConteudosDoUsuario(email, idUsuario);
		log.info("[finaliza] ConteudoRestController - buscaTodosOsConteudosDoUsuario");
		return conteudoResponse;
	}

	@Override
	public void editaConteudoPorId(String token, UUID idConteudo, ConteudoAlteracaoRequest ConteudoRequest) {
		log.info("[inicia] ConteudoRestController - editaConteudoPorId");
		String email = getUsuarioByToken(token);
		conteudoService.editaConteudoPorId(email, idConteudo, ConteudoRequest);
		log.info("[finaliza] ConteudoRestController - editaConteudoPorId");
	}

	@Override
	public void deletaConteudoPorId(String token, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - deletaConteudoPorId");
		String email = getUsuarioByToken(token);
		conteudoService.deletaConteudoPorId(email, idConteudo);
		log.info("[finaliza] ConteudoRestController - deletaConteudoPorId");
	}

	@Override
	public void ativaStatusComoRestritoDoConteudoPorId(String token, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - ativaStatusComoRestritoDoConteudoPorId");
		String email = getUsuarioByToken(token);
		conteudoService.ativaStatusComoRestritoDoConteudoPorId(email, idConteudo);
		log.info("[finaliza] ConteudoRestController - ativaStatusComoRestritoDoConteudoPorId");
	}


}
