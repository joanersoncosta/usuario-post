package dev.wakandaacademy.comentario.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.request.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.response.ComentarioResponse;
import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.application.service.ComentarioService;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
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
		post.incrementaQuantidadeComentario();
		postagemRepository.salva(post);
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
	
	private Comentario detalhaComentario(UUID idComentario) {
		log.info("[inicia] PostagemApplicationService - detalhaComentario");
		log.info("[idComentario], ", idComentario);
		Comentario comentario = comentarioRepository.buscaComentarioPorId(idComentario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - detalhaComentario");
		return comentario;
	}

	@Override
	public ComentarioResponse buscaComentarioPorId(String email, UUID idPostagem, UUID idConteudo, UUID idComentario) {
		log.info("[inicia] PostagemApplicationService - buscaComentarioPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		Comentario comentario = detalhaComentario(idComentario);
		if (comentario.getStatus().equals(StatusRestritoConteudo.ATIVO)) {
			throw APIException.build(HttpStatus.FORBIDDEN, "Comentário sensível não disponível!");
		}
		log.info("[finaliza] PostagemApplicationService - buscaComentarioPorId");
		return ComentarioResponse.converte(comentario);
	}

	@Override
	public List<ComentarioResponse> buscaTodosOsComentarios(String email, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - buscaTodosOsComentarios");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		List<Comentario> comentarioListResponse = comentarioRepository.buscaTodosOsComentarios(post.getIdPostagem());
		log.info("[finaliza] PostagemApplicationService - buscaTodosOsComentarios");
		return ComentarioResponse.converte(comentarioListResponse);
	}

	@Override
	public void editaComentario(String email, UUID idPostagem, UUID idConteudo, UUID idComentario,
			EditaComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - editaComentario");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertenceAoPost(post);
		comentario.editaComentario(comentarioRequest);
		comentarioRepository.salva(comentario);
		log.info("[finaliza] ComentarioApplicationService - editaComentario");
	}

	@Override
	public void deletaComentario(String email, UUID idPostagem, UUID idConteudo, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - deletaComentario");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertenceAoPost(post);
		comentario.pertenceAoUsuario(usuarioEmail);
		comentarioRepository.deletaComentario(comentario);
		post.decrementaQuantidadeComentario();
		postagemRepository.salva(post);
		log.info("[finaliza] ComentarioApplicationService - deletaComentario");
	}

	@Override
	public void ativaStatusRestritoComentarioPorId(String email, UUID idPostagem, UUID idConteudo, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - ativaStatusRestritoComentarioPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertenceAoPost(post);
		conteudo.pertenceAoUsuario(usuarioEmail);
		comentario.ativaStatusAtivacao();
		comentarioRepository.salva(comentario);
		log.info("[finaliza] ComentarioApplicationService - ativaStatusRestritoComentarioPorId");
	}

}
