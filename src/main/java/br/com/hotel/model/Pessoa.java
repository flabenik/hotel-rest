package br.com.hotel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	
	@Column(name = "documento", nullable = false, length = 80)
	private String documento;
	
	@Column(nullable = false, length = 80)
	private String telefone;
	
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "pessoa_hospedagem", joinColumns = { 
					@JoinColumn (name = "id_pessoa") },
			inverseJoinColumns = {
					@JoinColumn (name = "id_hospedagem")})
	private List<Hospedagem> hospedagens;
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

	public List<Hospedagem> getHospedagens() {
		return hospedagens;
	}

	public void setHospedagens(List<Hospedagem> hospedagens) {
		this.hospedagens = hospedagens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, hospedagens, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(hospedagens, other.hospedagens)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}

	

}
