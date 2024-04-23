package dev.wakandaacademy.conteudo.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;

@RestController
@RequestMapping("/v1/conteudo")
public interface ConteudoAPI {
	
	@PostMapping(value = "/cria-conteudo")
	@ResponseStatus(code = HttpStatus.CREATED)
	ConteudoIdResponse criaConteudo(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid ConteudoRequest ConteudoRequest);

}
