package dev.wakandaacademy.conteudo.infra.impl;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domain.Conteudo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ConteudoInfraRepository implements ConteudoRepository {

	@Override
	public Conteudo salva(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - salva");
		log.info("[finish] ConteudoInfraRepository - salva");
		return conteudo;

	}

}
