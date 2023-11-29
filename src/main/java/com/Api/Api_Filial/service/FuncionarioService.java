package com.Api.Api_Filial.service;

import com.Api.Api_Filial.dto.CepDTO;
import com.Api.Api_Filial.exception.DepartamentoNotFoundException;
import com.Api.Api_Filial.exception.FilialNotFoundException;
import com.Api.Api_Filial.exception.FuncionarioNotFoundException;
import com.Api.Api_Filial.model.Departamento;
import com.Api.Api_Filial.model.Filial;
import com.Api.Api_Filial.model.Funcionario;
import com.Api.Api_Filial.repository.DepartamentoRepository;
import com.Api.Api_Filial.repository.FilialRepository;
import com.Api.Api_Filial.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }


    public List<Funcionario> listarFuncionariosByFilialId(Integer filialId) {
        return funcionarioRepository.findByFilialId(filialId);
    }

    public Optional<Funcionario> listarFuncionariosFilialId(Integer funcionarioId, Integer filialId){
        return funcionarioRepository.findByIdAndFilialId(funcionarioId, filialId);
    }


    public Funcionario criarFuncionario(Integer filialId, Integer departamentoId, Funcionario novoFuncionario) {
        if (funcionarioRepository.findByCpfAndFilialId(novoFuncionario.getCpf(), filialId).isPresent()) {
            throw new FuncionarioNotFoundException("CPF já está em uso por outro Funcionário.");
        }

        Filial filial = filialRepository.findById(filialId)
                .orElseThrow(() -> new FilialNotFoundException("Filial não encontrada com o ID: " + filialId));
        novoFuncionario.setFilial(filial);

        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new DepartamentoNotFoundException("Departamento não encontrado com o ID: " + departamentoId));
        novoFuncionario.setDepartamento(departamento);

        String urlApiExterna = "https://brasilapi.com.br/api/cep/v1/" + novoFuncionario.getCep();
        RestTemplate restTemplate = new RestTemplate();
        CepDTO cepDTO = restTemplate.getForObject(urlApiExterna, CepDTO.class);

        if (cepDTO != null) {
            novoFuncionario.setCidade(cepDTO.getCity());
            novoFuncionario.setEstado(cepDTO.getState());
            novoFuncionario.setRua(cepDTO.getStreet());
            novoFuncionario.setBairro(cepDTO.getNeighborhood());
        }

        return funcionarioRepository.save(novoFuncionario);
    }

    @Transactional
    public Funcionario atualizarFuncionario(Integer filialId, Integer funcionarioId, Funcionario updatedFuncionario) {
        Funcionario existingFuncionario = funcionarioRepository.findByIdAndFilialId(funcionarioId, filialId)
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado com o ID: " + funcionarioId));
        existingFuncionario.setNome(updatedFuncionario.getNome());
        existingFuncionario.setCpf(updatedFuncionario.getCpf());
        existingFuncionario.setCep(updatedFuncionario.getCep());

        String urlApiExterna = "https://brasilapi.com.br/api/cep/v1/" + existingFuncionario.getCep();
        RestTemplate restTemplate = new RestTemplate();
        CepDTO cepDTO = restTemplate.getForObject(urlApiExterna, CepDTO.class);
        if (cepDTO != null) {
            existingFuncionario.setCidade(cepDTO.getCity());
            existingFuncionario.setEstado(cepDTO.getState());
            existingFuncionario.setRua(cepDTO.getStreet());
            existingFuncionario.setBairro(cepDTO.getNeighborhood());
            existingFuncionario.setCep(cepDTO.getCep());
        }
        return funcionarioRepository.save(existingFuncionario);
    }
    @Transactional
    public void deleteFuncionario(Integer filialId, Integer funcionarioId) {
        funcionarioRepository.deleteByIdAndFilialId(funcionarioId, filialId);
    }
}
