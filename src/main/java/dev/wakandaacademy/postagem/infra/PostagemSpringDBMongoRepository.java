package dev.wakandaacademy.postagem.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.postagem.domain.Postagem;

public interface PostagemSpringDBMongoRepository extends MongoRepository<Postagem, UUID>{

}
