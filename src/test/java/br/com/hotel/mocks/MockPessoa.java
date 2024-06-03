package br.com.hotel.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.hotel.data.CheckinPessoaVO;
import br.com.hotel.data.HospedagemVO;
import br.com.hotel.data.PessoaVO;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.model.Pessoa;

public class MockPessoa {
	
	public Pessoa mockEntity() {
		return mockEntity(0L);
	}
	
	public PessoaVO mockEntityVO() {
		return mockVO(0L);
	}
	
	public List<Pessoa> mockEntityList() {
        List<Pessoa> pessoa = new ArrayList<Pessoa>();
        for (long i = 0; i < 14; i++) {
        	pessoa.add(mockEntity(i));
        }
        return pessoa;
    }

    public List<PessoaVO> mockVOList() {
        List<PessoaVO> pessoas = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
        	pessoas.add(mockVO(i));
        }
        return pessoas;
    }
    
    public List<CheckinPessoaVO> mockEntityCheckInList() {
        List<CheckinPessoaVO> pessoa = new ArrayList<CheckinPessoaVO>();
        for (long i = 0; i < 14; i++) {
        	pessoa.add(mockEntityCheckIn(i));
        }
        return pessoa;
    }
	
	public Pessoa mockEntity(Long number) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(number);
		pessoa.setNome("Nome Teste" + number);
		pessoa.setDocumento("Documento" + number);
		pessoa.setTelefone("Telefone" + number);
		List<Hospedagem> hospedagem = new ArrayList<Hospedagem>();
		pessoa.setHospedagens(hospedagem);
        return pessoa;
    }
	
	public PessoaVO mockVO(Long number) {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setKey(number);
		pessoa.setNome("Nome Teste" + number);
		pessoa.setDocumento("Documento" + number);
		pessoa.setTelefone("Telefone" + number);
        return pessoa;
    }
	
	public CheckinPessoaVO mockEntityCheckIn(Long number) {
		CheckinPessoaVO pessoa = new CheckinPessoaVO();
		pessoa.setKey(number);
		pessoa.setNome("Nome Teste" + number);
		pessoa.setDocumento("Documento" + number);
		pessoa.setTelefone("Telefone" + number);
		List<HospedagemVO> hospedagem = new ArrayList<HospedagemVO>();
		pessoa.setHospedagens(hospedagem);
        return pessoa;
    }
	
	

}
