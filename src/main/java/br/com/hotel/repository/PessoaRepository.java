package br.com.hotel.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE CONCAT('%',:nome,'%')")
	Pessoa buscaHospedePorNome(@Param("nome") String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.telefone LIKE CONCAT('%',:telefone,'%')")
	Pessoa buscaPorTelefone(@Param("telefone") String telefone);

	@Query("SELECT p FROM Pessoa p WHERE p.documento LIKE CONCAT('%',:documento,'%')")
	Pessoa buscaPorDocumento(@Param("documento") String documento);
	
	@Query(nativeQuery = true, value = "SELECT ph.id_hospedagem, ph.id_pessoa, p.nome, p.documento, h.data_entrada, h.data_saida"
			 + " FROM pessoa_hospedagem ph "
			 + " JOIN pessoa p ON ph.id_pessoa = p.id "
			 + " JOIN hospedagem h ON ph.id_hospedagem = h.id "
			 + " WHERE  h.data_saida <= CURRENT_DATE + TIME '16:00:00'")
	List<Map<String, Object>> buscaHospedesHospedagemFinalizada();
	
	@Query(nativeQuery = true, value = "SELECT ph.id_hospedagem, ph.id_pessoa, p.nome, p.documento, h.data_entrada, h.data_saida"
			 + " FROM pessoa_hospedagem ph "
			 + " JOIN pessoa p ON ph.id_pessoa = p.id "
			 + " JOIN hospedagem h ON ph.id_hospedagem = h.id "
			 + " WHERE  h.data_saida > CURRENT_TIMESTAMP")
	List<Map<String, Object>> buscaHospedesHospedagemAndamento();
	
}