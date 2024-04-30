package dev.wakandaacademy.credencial.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.credencial.domain.Credencial;

public interface CredencialMongoSpringRepository extends MongoRepository<Credencial, UUID> {
	Optional<Credencial> findByUsuario(String Usuario);
}
