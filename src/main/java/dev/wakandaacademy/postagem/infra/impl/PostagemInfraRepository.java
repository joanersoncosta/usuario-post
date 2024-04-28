package dev.wakandaacademy.postagem.infra.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.infra.PostagemSpringDBMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PostagemInfraRepository implements PostagemRepository {
	private final PostagemSpringDBMongoRepository postagemSpringDBMongoRepository;

	@Override
	public Postagem salva(Postagem postagem) {
		log.info("[start] PostagemInfraRepository - salva");
		postagemSpringDBMongoRepository.save(postagem);
		log.info("[finish] PostagemInfraRepository - salva");
		return postagem;
	}

	@Override
	public Optional<Postagem> buscaPostPodId(UUID idPostagem) {
		log.info("[start] PostagemInfraRepository - buscaPostPodId");
		Optional<Postagem> post = postagemSpringDBMongoRepository.findById(idPostagem);
		log.info("[finish] PostagemInfraRepository - buscaPostPodId");
		return post;
	}

}
