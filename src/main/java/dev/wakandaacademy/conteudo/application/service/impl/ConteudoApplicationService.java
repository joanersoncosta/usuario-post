package dev.wakandaacademy.conteudo.application.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.application.service.ConteudoService;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConteudoApplicationService implements ConteudoService {
	private final UsuarioRepository usuarioRepository;
	private final ConteudoRepository conteudoRepository;

	@Override
	public ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest) {
		log.info("[inicia] ConteudoApplicationService - criaConteudo");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(conteudoRequest.idUsuario());
		usuario.pertenceAoUsuario(usuarioEmail);
		Conteudo conteudo = conteudoRepository.salva(new Conteudo(conteudoRequest));
		log.info("[finaliza] ConteudoApplicationService - criaConteudo");
		return ConteudoIdResponse.builder().idConteudo(conteudo.getIdConteudo()).build();
	}

	@Override
	public ConteudoResponse buscaConteudoPorId(String email, UUID idConteudo) {
		log.info("[inicia] ConteudoApplicationService - buscaConteudoPorId");
		log.info("[finaliza] ConteudoApplicationService - buscaConteudoPorId");
		return null;
	}

}
