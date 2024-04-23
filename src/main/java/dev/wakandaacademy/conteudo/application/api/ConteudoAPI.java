package dev.wakandaacademy.conteudo.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/conteudo")
public interface ConteudoAPI {
	
	@PostMapping(value = "/cria-conteudo")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cria conteudo")
	ConteudoIdResponse criaConteudo(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid ConteudoRequest ConteudoRequest);

	@GetMapping(value = "/{idConteudo}/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca conteudo por ID")
	ConteudoResponse buscaConteudoPorId(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idConteudo") UUID idConteudo);

	@GetMapping(value = "/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca Todos os conteudos")
	List<ConteudoResponse> buscaTodosOsConteudos(@RequestHeader(name = "Authorization", required = true) String token);

	@GetMapping(value = "/usuario/{idUsuario}/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca Todos os conteudos")
	List<ConteudoResponse> buscaTodosOsConteudosDoUsuario(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idUsuario") UUID idUsuario);

}
