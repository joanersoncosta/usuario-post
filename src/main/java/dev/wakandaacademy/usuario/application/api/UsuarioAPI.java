package dev.wakandaacademy.usuario.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.usuario.application.api.request.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.api.response.UsuarioIdResponse;

@RestController
@RequestMapping("/v1/usuario")
public interface UsuarioAPI {
	
	@PostMapping(path = "/public/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
//	@Operation(summary = "Cria um usuario")
	UsuarioIdResponse cadastraNovoUsuario(@RequestBody @Valid UsuarioNovoRequest usuarioRequest);

}
