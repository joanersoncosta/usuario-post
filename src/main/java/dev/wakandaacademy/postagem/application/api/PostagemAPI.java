package dev.wakandaacademy.postagem.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/v1/postagem")
public interface PostagemAPI {

	@PostMapping(value = "/cria-post")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cria post")
	@SecurityRequirement(name = "Bearer Authentication")
	PostagemIdResponse criaPostagem(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid PostagemRequest postagemRequest);

}
