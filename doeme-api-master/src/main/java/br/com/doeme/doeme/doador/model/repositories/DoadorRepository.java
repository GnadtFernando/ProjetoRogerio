package br.com.doeme.doeme.doador.model.repositories;


import br.com.doeme.doeme.doador.model.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long>{

}
