package com.Api.Api_Filial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "departamento",  cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Funcionario> funcionarios;
}
