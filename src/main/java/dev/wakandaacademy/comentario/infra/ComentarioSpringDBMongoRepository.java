package dev.wakandaacademy.comentario.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioSpringDBMongoRepository extends MongoRepository<Comentario, UUID>{

}
