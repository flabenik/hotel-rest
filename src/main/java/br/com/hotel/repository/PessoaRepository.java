package br.com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE CONCAT('%',:nome,'%')")
	Pessoa findPersonByName(@Param("nome") String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.telefone LIKE CONCAT('%',:telefone,'%')")
	Pessoa buscaPorTelefone(@Param("telefone") String telefone);

	@Query("SELECT p FROM Pessoa p WHERE p.documento LIKE CONCAT('%',:documento,'%')")
	Pessoa buscaPorDocumento(@Param("documento") String documento);
}
