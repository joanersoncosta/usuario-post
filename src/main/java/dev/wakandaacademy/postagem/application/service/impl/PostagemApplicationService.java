package dev.wakandaacademy.postagem.application.service.impl;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.service.PostagemService;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostagemApplicationService implements PostagemService {
	private final UsuarioRepository usuarioRepository;

	@Override
	public PostagemIdResponse criaPostagem(String email, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemApplicationService - criaPostagem");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(postagemRequest.idUsuario());
		usuario.pertenceAoUsuario(usuarioEmail);
		log.info("[finaliza] PostagemApplicationService - criaPostagem");
		return null;
	}

}
