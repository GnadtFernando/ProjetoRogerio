package br.com.doeme.doeme.pessoa.model.repositories;

import br.com.doeme.doeme.pessoa.model.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    Optional<Pessoa> findByName(String name);
}
