package dev.wakandaacademy.comentario.application.api.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.comentario.application.api.ComentarioAPI;
import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.request.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.response.ComentarioResponse;
import dev.wakandaacademy.comentario.application.service.ComentarioService;
import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ComentarioRestController implements ComentarioAPI {
	private final TokenService tokenService;
	private final ComentarioService comentarioService;

	private String getUsuarioByToken(String token) {
		log.info("[inicia] ComentarioRestController - getUsuarioByToken");
		log.debug("[token] {}", token);
		String emailUsuario = tokenService.getUsuarioByBearerToken(token)
				.orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, token));
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[finaliza] ComentarioRestController - getUsuarioByToken");
		return emailUsuario;
	}

	@Override
	public ComentarioIdResponse criaComentario(String token, ComentarioRequest postagemRequest) {
		log.info("[inicia] ComentarioRestController - criaComentario");
		String email = getUsuarioByToken(token);
		ComentarioIdResponse comentarioIdResponse = comentarioService.criaComentario(email, postagemRequest);
		log.info("[finaliza] ComentarioRestController - criaComentario");
		return comentarioIdResponse;
	}

	@Override
	public ComentarioResponse buscaComentarioPorId(String token, UUID idPostagem, UUID idConteudo, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - buscaComentarioPorId");
		String email = getUsuarioByToken(token);
		ComentarioResponse comentarioResponse = comentarioService.buscaComentarioPorId(email, idPostagem, idConteudo,
				idComentario);
		log.info("[finaliza] ComentarioRestController - buscaComentarioPorId");
		return comentarioResponse;
	}

	@Override
	public List<ComentarioResponse> buscaTodosOsComentarios(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ComentarioRestController - buscaTodosOsComentarios");
		String email = getUsuarioByToken(token);
		List<ComentarioResponse> comentarioResponse = comentarioService.buscaTodosOsComentarios(email, idPostagem,
				idConteudo);
		log.info("[finaliza] ComentarioRestController - buscaTodosOsComentarios");
		return comentarioResponse;

	}

	@Override
	public void editaComentario(String token, UUID idPostagem, UUID idConteudo, UUID idComentario,
			EditaComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - editaComentario");
		String email = getUsuarioByToken(token);
		comentarioService.editaComentario(email, idPostagem, idConteudo, idComentario, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - editaComentario");
	}

}
