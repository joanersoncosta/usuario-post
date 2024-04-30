package dev.wakandaacademy.comentario.application.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.application.service.ComentarioService;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ComentarioApplicationService implements ComentarioService {
	private final UsuarioRepository usuarioRepository;
	private final ConteudoRepository conteudoRepository;
	private final PostagemRepository postagemRepository;
	private final ComentarioRepository comentarioRepository;
	
	@Override
	public ComentarioIdResponse criaComentario(String email, ComentarioRequest postagemRequest) {
		log.info("[inicia] ComentarioApplicationService - criaComentario");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(postagemRequest.idUsuario());
		Conteudo conteudo = detalhaConteudo(postagemRequest.idConteudo());
		conteudo.pertenceAoConteudo(usuario);
		Postagem post = detalhaPost(postagemRequest.idPostagem());
		post.pertenceAoConteudo(conteudo);
		Comentario comentario = comentarioRepository.salva(new Comentario(postagemRequest, post.getIdPublicador(), usuarioEmail));
		log.info("[finaliza] ComentarioApplicationService - criaComentario");
		return ComentarioIdResponse.builder().idComentario(comentario.getIdComentario()).build();
	}
	
	private Conteudo detalhaConteudo(UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - detalhaConteudo");
		log.info("[idConteudo], ", idConteudo);
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - detalhaConteudo");
		return conteudo;
	}
	private Postagem detalhaPost(UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - detalhaPost");
		log.info("[idPostagem], ", idPostagem);
		Postagem post = postagemRepository.buscaPostPodId(idPostagem)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - detalhaPost");
		return post;
	}

}
