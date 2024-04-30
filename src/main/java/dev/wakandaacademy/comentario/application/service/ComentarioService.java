package dev.wakandaacademy.comentario.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.request.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.response.ComentarioResponse;

public interface ComentarioService {

	ComentarioIdResponse criaComentario(String email, ComentarioRequest postagemRequest);

	ComentarioResponse buscaComentarioPorId(String email, UUID idPostagem, UUID idConteudo, UUID idComentario);

	List<ComentarioResponse> buscaTodosOsComentarios(String email, UUID idPostagem, UUID idConteudo);

	void editaComentario(String email, UUID idPostagem, UUID idConteudo, UUID idComentario,
			EditaComentarioRequest comentarioRequest);

	void deletaComentario(String email, UUID idPostagem, UUID idConteudo, UUID idComentario);

	void ativaStatusRestritoComentarioPorId(String email, UUID idPostagem, UUID idConteudo, UUID idComentario);

	void usuarioLikeComentario(String email, UUID idPostagem, UUID idConteudo, UUID idComentario);

}
