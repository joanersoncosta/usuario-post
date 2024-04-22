package dev.wakandaacademy.credencial.application.service.impl;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.credencial.application.repository.CredencialRepository;
import dev.wakandaacademy.credencial.application.service.CredencialService;
import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.credencial.domain.perfis.CredencialUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredencialApplicationService implements CredencialService {
	private final CredencialRepository credencialRepository;

	@Override
	public void criaNovaCredencial(CredencialUsuario usuario) {
		log.info("[inicia] CrendencialService - criaNovaCredencial");
		credencialRepository.salva(new Credencial(usuario.getEmail(), usuario.getSenha(), usuario.getPerfil()));
		log.info("[finaliza] CrendencialService - criaNovaCredencial");
	}
	
	@Override
	public Credencial buscaCredencialPorUsuario(String usuario) {
		log.info("[inicia] CredencialSpringDataJpaService - buscaCredencialPorUsuario");
		Credencial credencial = credencialRepository.buscaCredencialPorUsuario(usuario);
		log.info("[finaliza] CredencialSpringDataJpaService - buscaCredencialPorUsuario");
		return credencial;
	}

}
