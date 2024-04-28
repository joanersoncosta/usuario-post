package dev.wakandaacademy.postagem.application.service;

import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.response.PostagemResponse;

public interface PostagemService {

	PostagemIdResponse criaPostagem(String email, PostagemRequest postagemRequest);

	PostagemResponse buscaPostPorId(String email, UUID idPostagem, UUID idConteudo);

}
