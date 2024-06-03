package br.com.hotel.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.hotel.data.PessoaVO;
import br.com.hotel.mocks.MockPessoa;
import br.com.hotel.model.Pessoa;

public class DozerConverterTest {
    
    MockPessoa inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPessoa();
    }

    @Test
    public void parseEntityToVOTest() {
        PessoaVO output = ModMapper.parseObject(inputObject.mockEntity(), PessoaVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Nome Teste0", output.getNome());
        assertEquals("Documento0", output.getDocumento());
        assertEquals("Telefone0", output.getTelefone());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PessoaVO> outputList = ModMapper.parseListObjects(inputObject.mockEntityList(), PessoaVO.class);
        PessoaVO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Nome Teste0", outputZero.getNome());
        assertEquals("Documento0", outputZero.getDocumento());
        assertEquals("Telefone0", outputZero.getTelefone());
        
        PessoaVO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Nome Teste7", outputSeven.getNome());
        assertEquals("Documento7", outputSeven.getDocumento());
        assertEquals("Telefone7", outputSeven.getTelefone());
        
        PessoaVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Nome Teste12", outputTwelve.getNome());
        assertEquals("Documento12", outputTwelve.getDocumento());
        assertEquals("Telefone12", outputTwelve.getTelefone());
    }

    @Test
    public void parseVOToEntityTest() {
        Pessoa output = ModMapper.parseObject(inputObject.mockVO(0L), Pessoa.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome Teste0", output.getNome());
        assertEquals("Documento0", output.getDocumento());
        assertEquals("Telefone0", output.getTelefone());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Pessoa> outputList = ModMapper.parseListObjects(inputObject.mockVOList(), Pessoa.class);
        Pessoa outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Nome Teste0", outputZero.getNome());
        assertEquals("Documento0", outputZero.getDocumento());
        assertEquals("Telefone0", outputZero.getTelefone());
        
        Pessoa outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Nome Teste7", outputSeven.getNome());
        assertEquals("Documento7", outputSeven.getDocumento());
        assertEquals("Telefone7", outputSeven.getTelefone());
        
        Pessoa outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Nome Teste12", outputTwelve.getNome());
        assertEquals("Documento12", outputTwelve.getDocumento());
        assertEquals("Telefone12", outputTwelve.getTelefone());
    }
}
