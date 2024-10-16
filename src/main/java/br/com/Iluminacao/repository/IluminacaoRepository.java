package br.com.Iluminacao.repository;

import br.com.Iluminacao.model.Iluminacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IluminacaoRepository extends JpaRepository<Iluminacao, Long> {

}
