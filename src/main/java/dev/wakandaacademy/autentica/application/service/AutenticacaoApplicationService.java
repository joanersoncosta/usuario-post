package dev.wakandaacademy.autentica.application.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import dev.wakandaacademy.autentica.domain.Token;

public interface AutenticacaoApplicationService {
	Token autentica(UsernamePasswordAuthenticationToken userCredentials);

	Token reativaToken(String tokenExpirado);

}
