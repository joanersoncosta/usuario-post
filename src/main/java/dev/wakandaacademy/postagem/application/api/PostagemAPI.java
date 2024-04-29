package dev.wakandaacademy.postagem.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.postagem.application.api.request.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemListResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemResponse;
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

	@PostMapping(value = "/{idPostagem}/conteudo/{idConteudo}/busca")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca post por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	PostagemResponse buscaPostPorId(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo);

	@PostMapping(value = "/conteudo/{idConteudo}/busca")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Lista todos os posts por conteúdo")
	@SecurityRequirement(name = "Bearer Authentication")
	List<PostagemListResponse> buscaTodosOsPostPorIdConteudo(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idConteudo") UUID idConteudo);

	@PostMapping(value = "/{idPostagem}/conteudo/{idConteudo}/busca")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Busca post por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void editaPost(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo, @RequestBody @Valid EditaPostagemRequest postagemRequest);

}
