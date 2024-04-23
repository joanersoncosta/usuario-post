package dev.wakandaacademy.conteudo.infra.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.conteudo.infra.ConteudoSpringDBMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ConteudoInfraRepository implements ConteudoRepository {
	private final ConteudoSpringDBMongoRepository conteudoSpringDBMongoRepository;
	private MongoTemplate mongoTemplate;
	
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
		Optional<Conteudo> conteudo = conteudoSpringDBMongoRepository.findById(idUsuario);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return conteudo;
	}

	@Override
	public List<Conteudo> buscaTodosOsConteudos() {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(StatusRestritoConteudo.INAVITO));
		
		List<Conteudo> conteudos = mongoTemplate.find(query, Conteudo.class);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return conteudos;
	}

	@Override
	public List<Conteudo> buscaTodosOsConteudosDoUsuario(UUID idUsuario) {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		Query query = new Query();
		query.addCriteria(Criteria.where("idUsuario").is(idUsuario)
				.and("status").is(StatusRestritoConteudo.INAVITO));
		
		List<Conteudo> conteudos = mongoTemplate.find(query, Conteudo.class);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return conteudos;
	}

	@Override
	public void deletaConteudo(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - salva");
		conteudoSpringDBMongoRepository.delete(conteudo);
		log.info("[finish] ConteudoInfraRepository - salva");
	}

}
