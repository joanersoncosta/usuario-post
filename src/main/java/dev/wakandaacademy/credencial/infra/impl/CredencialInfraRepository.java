package dev.wakandaacademy.credencial.infra.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.credencial.application.repository.CredencialRepository;
import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.credencial.infra.CredencialMongoSpringRepository;
import dev.wakandaacademy.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CredencialInfraRepository implements CredencialRepository {
	private final CredencialMongoSpringRepository credencialMongoRepository;

	@Override
	public void salva(Credencial credencial) {
		log.info("[start] CredencialRepositoryMongoDB - salva");
		try {
			credencialMongoRepository.save(credencial);
		}catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Usuário já cadastrado.");
		}	
		log.info("[finish] CredencialRepositoryMongoDB - salva");
	}

}
