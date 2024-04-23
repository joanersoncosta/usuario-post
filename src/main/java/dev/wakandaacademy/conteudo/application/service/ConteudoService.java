package dev.wakandaacademy.conteudo.application.service;

import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

	ConteudoResponse buscaConteudoPorId(String email, UUID idConteudo);

}
