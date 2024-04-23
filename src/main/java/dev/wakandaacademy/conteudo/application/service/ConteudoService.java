package dev.wakandaacademy.conteudo.application.service;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

}
