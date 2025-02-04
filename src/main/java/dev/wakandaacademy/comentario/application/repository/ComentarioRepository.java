package dev.wakandaacademy.comentario.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioRepository {

	Comentario salva(Comentario comentario);

	Optional<Comentario> buscaComentarioPorId(UUID idComentario);

	List<Comentario> buscaTodosOsComentarios(UUID idPostagem);

	void deletaComentario(Comentario comentario);

}
