package dev.wakandaacademy.comentario.application.api;

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

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.request.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.response.ComentarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/v1/comentario")
public interface ComentarioAPI {

	@PostMapping(value = "/cria-comentario")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cria comentario")
	@SecurityRequirement(name = "Bearer Authentication")
	ComentarioIdResponse criaComentario(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid ComentarioRequest postagemRequest);

	@GetMapping(value = "/{idComentario}/conteudo/{idConteudo}/postagem/{idPostagem}/busca")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca comentario por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	ComentarioResponse buscaComentarioPorId(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idComentario") UUID idComentario);

	@GetMapping(value = "/conteudo/{idConteudo}/postagem/{idPostagem}/busca")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Busca comentario por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	List<ComentarioResponse> buscaTodosOsComentarios(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo);

	@PatchMapping(value = "/{idComentario}/conteudo/{idConteudo}/postagem/{idPostagem}/edita")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Edita post por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void editaComentario(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idComentario") UUID idComentario, @RequestBody @Valid EditaComentarioRequest comentarioRequest);

	@DeleteMapping(value = "/{idComentario}/conteudo/{idConteudo}/postagem/{idPostagem}/deleta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deleta post por ID")
	@SecurityRequirement(name = "Bearer Authentication")
	void deletaComentario(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idComentario") UUID idComentario);

}
