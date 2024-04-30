package dev.wakandaacademy.comentario.infra.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.comentario.infra.ComentarioSpringDBMongoRepository;
import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ComentarioInfraRepository implements ComentarioRepository {
	private final ComentarioSpringDBMongoRepository comentarioSpringDBMongoRepository;
	private final MongoTemplate mongoTemplate;

	@Override
	public Comentario salva(Comentario comentario) {
		log.info("[start] ComentarioInfraRepository - salva");
		comentarioSpringDBMongoRepository.save(comentario);
		log.info("[finish] ComentarioInfraRepository - salva");
		return null;
	}

	@Override
	public Optional<Comentario> buscaComentarioPorId(UUID idComentario) {
		log.info("[start] ComentarioInfraRepository - buscaComentarioPorId");
		Optional<Comentario> comentario = comentarioSpringDBMongoRepository.findById(idComentario);
		log.info("[finish] ComentarioInfraRepository - buscaComentarioPorId");
		return comentario;
	}

	@Override
	public List<Comentario> buscaTodosOsComentarios(UUID idPostagem) {
		log.info("[start] ComentarioInfraRepository - buscaTodosOsComentarios");
		Query query = new Query();
		query.addCriteria(Criteria.where("idPostagem").is(idPostagem).and("status").is(StatusRestritoConteudo.INAVITO));

		List<Comentario> comentarios = mongoTemplate.find(query, Comentario.class);
		log.info("[finish] ComentarioInfraRepository - buscaTodosOsComentarios");
		return comentarios;
	}

}
