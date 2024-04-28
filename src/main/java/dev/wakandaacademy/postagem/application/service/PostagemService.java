package dev.wakandaacademy.postagem.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemListResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemResponse;

public interface PostagemService {

	PostagemIdResponse criaPostagem(String email, PostagemRequest postagemRequest);

	PostagemResponse buscaPostPorId(String email, UUID idPostagem, UUID idConteudo);

	List<PostagemListResponse> buscaTodosOsPostPorIdConteudo(String email, UUID idConteudo);

}
