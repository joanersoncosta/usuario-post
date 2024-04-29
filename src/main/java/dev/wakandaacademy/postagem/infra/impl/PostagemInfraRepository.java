package dev.wakandaacademy.postagem.infra.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.domain.enuns.StatusRestritoConteudo;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
import dev.wakandaacademy.postagem.infra.PostagemSpringDBMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PostagemInfraRepository implements PostagemRepository {
	private final PostagemSpringDBMongoRepository postagemSpringDBMongoRepository;
	private final MongoTemplate mongoTemplate;

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

	@Override
	public List<Postagem> buscaTodosOsPostPorIdConteudo(UUID idConteudo) {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		Query query = new Query();
		query.addCriteria(Criteria.where("idConteudo").is(idConteudo).and("status").is(StatusRestritoConteudo.INAVITO));

		List<Postagem> posts = mongoTemplate.find(query, Postagem.class);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return posts;
	}

	@Override
	public void deletaPost(Postagem post) {
		log.info("[start] ConteudoInfraRepository - deletaPost");
		postagemSpringDBMongoRepository.delete(post);
		log.info("[finish] ConteudoInfraRepository - deletaPost");
	}

	@Override
	public void desativaTodosOsPosts(UUID idConteudo) {
		log.info("[start] ConteudoInfraRepository - desativaTodosOsPosts");
		Query query = new Query();
		query.addCriteria(Criteria.where("idConteudo").is(idConteudo).and("status").is(StatusRestritoConteudo.INAVITO));

		Update update= new Update();
		update.set("statusAtivacao", StatusAtivacaoPostagem.INATIVO);
		mongoTemplate.updateFirst(query, update, Postagem.class);
		log.info("[finish] ConteudoInfraRepository - desativaTodosOsPosts");
	}

}
