package dev.wakandaacademy.conteudo.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.conteudo.domain.Conteudo;

public interface ConteudoSpringDBMongoRepository extends MongoRepository<Conteudo, UUID>{

}
