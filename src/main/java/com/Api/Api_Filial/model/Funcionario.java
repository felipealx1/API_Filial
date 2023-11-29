package com.Api.Api_Filial.model;

import com.Api.Api_Filial.dto.CepDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private  String estado;

    @Column
    private  String rua;

    @Column
    private String bairro;


    @ManyToOne
    @JoinColumn(name = "filial_id")
    @JsonBackReference
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;


    public Funcionario (CepDTO cepDTO){
        this.cep = cepDTO.getCep();
        this.estado = cepDTO.getState();
        this.cidade = cepDTO.getCity();
        this.bairro = cepDTO.getNeighborhood();
    }



}
