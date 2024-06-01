package br.com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Hospedagem;

@Repository
public interface HospedagemRepository extends JpaRepository<Hospedagem, Long>{
	

}
