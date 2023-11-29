package com.Api.Api_Filial.repository;

import com.Api.Api_Filial.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findByFilialId(Integer filialId);
    Optional<Funcionario> findByIdAndFilialId(Integer funcionarioId, Integer filialId);

    Optional<Funcionario> findByCpfAndFilialId(String cpf, Integer filialId);
    void deleteByIdAndFilialId(Integer funcionarioId, Integer filialId);
}
