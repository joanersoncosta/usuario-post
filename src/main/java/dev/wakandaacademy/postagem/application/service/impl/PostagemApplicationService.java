package dev.wakandaacademy.postagem.application.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemListResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemResponse;
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
		Postagem postagem = postagemRepository.salva(new Postagem(postagemRequest, usuarioEmail));
		log.info("[finaliza] PostagemApplicationService - criaPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}

	@Override
	public PostagemResponse buscaPostPorId(String email, UUID idPostagem, UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - buscaPostPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], [idConteudo]", idPostagem, idConteudo);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		Postagem post = detalhaPost(idPostagem);
		post.pertenceAoConteudo(conteudo);
		if (post.getStatus().equals(StatusRestritoConteudo.ATIVO)) {
			throw APIException.build(HttpStatus.FORBIDDEN, "Post sensível não disponível!");
		}
		log.info("[finaliza] PostagemApplicationService - buscaPostPorId");
		return PostagemResponse.converte(post);
	}

	private Postagem detalhaPost(UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - detalhaPost");
		Postagem post = postagemRepository.buscaPostPodId(idPostagem)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - detalhaPost");
		return post;
	}

	private Conteudo detalhaConteudo(UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - detalhaConteudo");
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - detalhaConteudo");
		return conteudo;
	}

	@Override
	public List<PostagemListResponse> buscaTodosOsPostPorIdConteudo(String email, UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - buscaTodosOsPostPorIdConteudo");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idConteudo]", idConteudo);
		Conteudo conteudo = detalhaConteudo(idConteudo);
		if (conteudo.getStatus().equals(StatusRestritoConteudo.ATIVO)) {
			throw APIException.build(HttpStatus.FORBIDDEN, "Conteúdo sensível não disponível!");
		}
		List<Postagem> posts = postagemRepository.buscaTodosOsPostPorIdConteudo(conteudo.getIdConteudo());
		log.info("[finaliza] PostagemApplicationService - buscaTodosOsPostPorIdConteudo");
		return PostagemListResponse.converte(posts);
	}

}
