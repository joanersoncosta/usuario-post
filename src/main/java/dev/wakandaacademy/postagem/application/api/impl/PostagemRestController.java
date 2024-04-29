package dev.wakandaacademy.postagem.application.api.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAPI;
import dev.wakandaacademy.postagem.application.api.request.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemListResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemResponse;
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

	@Override
	public PostagemResponse buscaPostPorId(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - buscaPostPorId");
		String email = getUsuarioByToken(token);
		PostagemResponse postagemResponse = postagemService.buscaPostPorId(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - buscaPostPorId");
		return postagemResponse;
	}

	@Override
	public List<PostagemListResponse> buscaTodosOsPostPorIdConteudo(String token, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - buscaTodosOsPostPorIdConteudo");
		String email = getUsuarioByToken(token);
		List<PostagemListResponse> postagemListResponse = postagemService.buscaTodosOsPostPorIdConteudo(email,
				idConteudo);
		log.info("[finaliza] ConteudoRestController - buscaTodosOsPostPorIdConteudo");
		return postagemListResponse;
	}

	@Override
	public void editaPost(String token, UUID idPostagem, UUID idConteudo, EditaPostagemRequest postagemRequest) {
		log.info("[inicia] ConteudoRestController - editaPost");
		String email = getUsuarioByToken(token);
		postagemService.editaPost(email, idPostagem, idConteudo, postagemRequest);
		log.info("[finaliza] ConteudoRestController - editaPost");
	}

	@Override
	public void deletaPostPorId(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - deletaPostPorId");
		String email = getUsuarioByToken(token);
		postagemService.deletaPostPorId(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - deletaPostPorId");
	}

	@Override
	public void ativaStatusRestritoPostPorId(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - ativaStatusRestritoPostPorId");
		String email = getUsuarioByToken(token);
		postagemService.ativaStatusRestritoPostPorId(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - ativaStatusRestritoPostPorId");
	}

	@Override
	public void usuarioLikePostagem(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - usuarioLikePostagem");
		String email = getUsuarioByToken(token);
		postagemService.usuarioLikePostagem(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - usuarioLikePostagem");
	}

	@Override
	public void usuarioDeslikePostagem(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - usuarioDeslikePostagem");
		String email = getUsuarioByToken(token);
		postagemService.usuarioDeslikePostagem(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - usuarioDeslikePostagem");
	}

	@Override
	public void ativaStatusPostPorId(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - ativaStatusPostPorId");
		String email = getUsuarioByToken(token);
		postagemService.ativaStatusPostPorId(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - ativaStatusPostPorId");
	}

	@Override
	public void desativaStatusPostPorId(String token, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - desativaStatusPostPorId");
		String email = getUsuarioByToken(token);
		postagemService.desativaStatusPostPorId(email, idPostagem, idConteudo);
		log.info("[finaliza] ConteudoRestController - desativaStatusPostPorId");
	}

}
