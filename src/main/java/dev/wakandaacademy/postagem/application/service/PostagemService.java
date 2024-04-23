package dev.wakandaacademy.postagem.application.service;

import dev.wakandaacademy.postagem.application.api.request.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.response.PostagemIdResponse;

public interface PostagemService {

	PostagemIdResponse criaPostagem(String email, PostagemRequest postagemRequest);

}
