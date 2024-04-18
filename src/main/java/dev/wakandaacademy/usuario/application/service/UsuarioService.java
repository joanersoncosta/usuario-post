package dev.wakandaacademy.usuario.application.service;

import dev.wakandaacademy.usuario.application.api.request.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.api.response.UsuarioIdResponse;

public interface UsuarioService {

	UsuarioIdResponse cadastraNovoUsuario(UsuarioNovoRequest usuarioRequest);

}
