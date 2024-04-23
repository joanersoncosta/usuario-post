package dev.wakandaacademy.conteudo.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ConteudoRestController implements ConteudoAPI {

	@Override
	public ConteudoIdResponse criaConteudo(String token, ConteudoRequest ConteudoRequest) {
		log.info("[inicia] ConteudoRestController - criaConteudo");
		log.info("[finaliza] ConteudoRestController - criaConteudo");
		return null;
	}

}
