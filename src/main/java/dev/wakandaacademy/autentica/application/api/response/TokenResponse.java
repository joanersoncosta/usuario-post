package dev.wakandaacademy.autentica.application.api.response;

import dev.wakandaacademy.autentica.domain.Token;
import lombok.Value;

@Value
public class TokenResponse {
	private String token;
	private String tipo;

	public TokenResponse(Token token) {
		this.token = token.getToken();
		this.tipo = token.getTipo();
	}
}