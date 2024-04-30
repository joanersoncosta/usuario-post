package dev.wakandaacademy.comentario.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/v1/comentario")
public interface ComentarioAPI {

	@PostMapping(value = "/cria-comentario")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cria post")
	@SecurityRequirement(name = "Bearer Authentication")
	ComentarioIdResponse criaComentario(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid ComentarioRequest postagemRequest);

}
