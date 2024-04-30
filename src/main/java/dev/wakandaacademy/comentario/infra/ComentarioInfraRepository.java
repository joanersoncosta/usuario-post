package dev.wakandaacademy.comentario.infra;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
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

}
