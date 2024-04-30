package dev.wakandaacademy.autentica.application.api;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.wakandaacademy.autentica.application.api.request.AutenticacaoRequest;
import dev.wakandaacademy.autentica.application.api.response.TokenResponse;

@RequestMapping("/public/v1/autenticacao")
public interface AutenticacaoAPI {

	@PostMapping
	TokenResponse autentica(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) throws AuthenticationException;

	@PostMapping("/reativacao")
	TokenResponse reativaAutenticacao(@RequestHeader("Authorization") String tokenExpirado)
			throws AuthenticationException;

}
