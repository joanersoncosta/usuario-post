package dev.wakandaacademy.conteudo.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.conteudo.domain.Conteudo;

public interface ConteudoRepository {

	Conteudo salva(Conteudo conteudo);

	Optional<Conteudo> buscaConteudoPorId(UUID idConteudo);

	List<Conteudo> buscaTodosOsConteudos();

	List<Conteudo> buscaTodosOsConteudosDoUsuario(UUID idUsuario);

	void deletaConteudo(Conteudo conteudo);

}
