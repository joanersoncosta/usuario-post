package dev.wakandaacademy.credencial.application.service;

import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.credencial.domain.perfis.CredencialUsuario;

public interface CredencialService {
	
	void criaNovaCredencial(CredencialUsuario usuario);

	Credencial buscaCredencialPorUsuario(String usuario);
	
}
