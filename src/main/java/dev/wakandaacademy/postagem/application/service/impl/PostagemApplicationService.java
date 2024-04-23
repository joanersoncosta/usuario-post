package dev.wakandaacademy.postagem.application.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.application.service.PostagemService;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostagemApplicationService implements PostagemService {
	private final UsuarioRepository usuarioRepository;
	private final ConteudoRepository conteudoRepository;
	private final PostagemRepository postagemRepository;

	@Override
	public PostagemIdResponse criaPostagem(String email, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemApplicationService - criaPostagem");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(postagemRequest.idUsuario());
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(postagemRequest.idConteudo())
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		conteudo.pertenceAoUsuario(usuario);
		Postagem postagem  = postagemRepository.salva(new Postagem(postagemRequest, usuarioEmail));
		log.info("[finaliza] PostagemApplicationService - criaPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}

}
