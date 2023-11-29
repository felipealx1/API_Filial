package com.Api.Api_Filial.service;

import com.Api.Api_Filial.exception.DepartamentoNotFoundException;
import com.Api.Api_Filial.model.Departamento;
import com.Api.Api_Filial.repository.DepartamentoRepository;
import com.Api.Api_Filial.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> listarDepartamentoId(Integer departamentoId) {
        return departamentoRepository.findById(departamentoId);
    }

    public Departamento criarDepartamento(Departamento novoDepartamento) {
        return departamentoRepository.save(novoDepartamento);
    }


    public Departamento atualizarDepartamento(Integer id, Departamento updatedDepartamento) {
        Departamento existingDepartamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException("Departamento não encontrado com o ID: " + id));

        existingDepartamento.setNome(updatedDepartamento.getNome());
        return departamentoRepository.save(existingDepartamento);
    }


    public void deletarDepartamento(Integer id) {
        departamentoRepository.deleteById(id);
    }
}
