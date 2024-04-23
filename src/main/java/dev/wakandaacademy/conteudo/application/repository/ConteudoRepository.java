package dev.wakandaacademy.conteudo.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.conteudo.domain.Conteudo;

public interface ConteudoRepository {

	Conteudo salva(Conteudo conteudo);

	Optional<Conteudo> buscaConteudoPorId(UUID idUsuario);

	List<Conteudo> buscaTodosOsConteudos();

}
