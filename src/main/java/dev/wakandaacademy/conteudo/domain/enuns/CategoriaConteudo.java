package dev.wakandaacademy.conteudo.domain.enuns;

import java.util.Arrays;
import java.util.Optional;

public enum CategoriaConteudo {
	TECNOLOGIA("TECNOLOGIA"), VIAJEM("VIAJEM"), PET("PET");

	private String nicho;

	CategoriaConteudo(String nicho) {
		this.nicho = nicho;
	}

	public String getNicho() {
		return this.nicho;
	}

	public static Optional<CategoriaConteudo> validaCategoria(String nicho) {
		return Arrays.stream(values()).filter(valorCorrespondente -> valorCorrespondente.getNicho().equals(nicho))
				.findFirst();
	}
}
