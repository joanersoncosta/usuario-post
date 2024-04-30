package dev.wakandaacademy.comentario.application.repository;

import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioRepository {

	Comentario salva(Comentario comentario);

	Optional<Comentario> buscaComentarioPorId(UUID idComentario);

}
