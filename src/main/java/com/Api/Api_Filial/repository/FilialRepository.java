package com.Api.Api_Filial.repository;

import com.Api.Api_Filial.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {
    boolean existsByCnpj(String cnpj);
}
