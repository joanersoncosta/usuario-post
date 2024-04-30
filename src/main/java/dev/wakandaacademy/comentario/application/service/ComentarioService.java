package dev.wakandaacademy.comentario.application.service;

import dev.wakandaacademy.comentario.application.api.request.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.response.ComentarioIdResponse;

public interface ComentarioService {

	ComentarioIdResponse criaComentario(String email, ComentarioRequest postagemRequest);

}
