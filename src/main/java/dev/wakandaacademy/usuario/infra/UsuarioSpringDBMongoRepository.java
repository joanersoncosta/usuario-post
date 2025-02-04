package dev.wakandaacademy.usuario.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.usuario.domain.Usuario;

public interface UsuarioSpringDBMongoRepository extends MongoRepository<Usuario, UUID>{

	Optional<Usuario> findByEmail(String email);

}
