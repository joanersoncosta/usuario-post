package dev.wakandaacademy.conteudo.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.request.ConteudoAlteracaoRequest;
import dev.wakandaacademy.conteudo.application.api.request.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.response.ConteudoResponse;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

	ConteudoResponse buscaConteudoPorId(String email, UUID idConteudo);

	List<ConteudoResponse> buscaTodosOsConteudos(String email);

	List<ConteudoResponse> buscaTodosOsConteudosDoUsuario(String email, UUID idUsuario);

	void editaConteudoPorId(String email, UUID idConteudo, ConteudoAlteracaoRequest conteudoRequest);

	void deletaConteudoPorId(String email, UUID idConteudo);

}
