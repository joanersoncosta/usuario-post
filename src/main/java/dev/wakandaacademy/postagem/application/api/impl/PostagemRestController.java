package dev.wakandaacademy.postagem.application.api.impl;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.postagem.application.api.PostagemAPI;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {

	@Override
	public PostagemIdResponse criaPostagem(String token, PostagemRequest postagemRequest) {
		log.info("[inicia] ConteudoRestController - criaPostagem");
		log.info("[finaliza] ConteudoRestController - criaPostagem");
		return null;
	}

}
