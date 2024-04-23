package dev.wakandaacademy.conteudo.infra.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.conteudo.infra.ConteudoSpringDBMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ConteudoInfraRepository implements ConteudoRepository {
	private final ConteudoSpringDBMongoRepository conteudoSpringDBMongoRepository;

	@Override
	public Conteudo salva(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - salva");
		conteudoSpringDBMongoRepository.save(conteudo);
		log.info("[finish] ConteudoInfraRepository - salva");
		return conteudo;
	}

	@Override
	public Optional<Conteudo> buscaConteudoPorId(UUID idUsuario) {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return Optional.empty();
	}

}
