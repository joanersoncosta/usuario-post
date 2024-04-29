package dev.wakandaacademy.postagem.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.postagem.domain.Postagem;

public interface PostagemRepository {

	Postagem salva(Postagem postagem);

	Optional<Postagem> buscaPostPodId(UUID idPostagem);

	List<Postagem> buscaTodosOsPostPorIdConteudo(UUID idConteudo);

	void deletaPost(Postagem post);

	void desativaTodosOsPosts(UUID idConteudo);

}
