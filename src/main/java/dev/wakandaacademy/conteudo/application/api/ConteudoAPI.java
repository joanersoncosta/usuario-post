package dev.wakandaacademy.conteudo.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoAlteracaoRequest;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/v1/conteudo")
public interface ConteudoAPI {

	@PostMapping(value = "/cria-conteudo")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cria conteudo")
	@SecurityRequirement(name = "Bearer Authentication")
	ConteudoIdResponse criaConteudo(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid ConteudoRequest ConteudoRequest);

	@GetMapping(value = "/{idConteudo}/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca conteudo por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	ConteudoResponse buscaConteudoPorId(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable(value = "idConteudo") UUID idConteudo);

	@GetMapping(value = "/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca Todos os conteudos")
	@SecurityRequirement(name = "Bearer Authentication")
	List<ConteudoResponse> buscaTodosOsConteudos(@RequestHeader(name = "Authorization", required = true) String token);

	@GetMapping(value = "/usuario/{idUsuario}/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca Todos os conteudos do usuário")
	@SecurityRequirement(name = "Bearer Authentication")
	List<ConteudoResponse> buscaTodosOsConteudosDoUsuario(
			@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable(value = "idUsuario") UUID idUsuario);

	@PatchMapping(value = "/{idConteudo}/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Edita conteudo por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void editaConteudoPorId(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable(value = "idConteudo") UUID idConteudo,
			@RequestBody @Valid ConteudoAlteracaoRequest ConteudoRequest);

	@DeleteMapping(value = "/{idConteudo}/deleta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deleta conteudo por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void deletaConteudoPorId(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable(value = "idConteudo") UUID idConteudo);

	@PatchMapping(value = "/{idConteudo}/restrito")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Ativa status como restrito do conteudo por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void ativaStatusComoRestritoDoConteudoPorId(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable(value = "idConteudo") UUID idConteudo);

}
