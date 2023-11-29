package com.Api.Api_Filial.controller;

import com.Api.Api_Filial.model.Departamento;
import com.Api.Api_Filial.service.DepartamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Operation(summary = "Listar todos os Departamentos")
    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        List<Departamento> departamentos = departamentoService.listarDepartamentos();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }
    @Operation(summary = "Listar o Departamento por meio do seu id")
    @GetMapping("/{departamentoId}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Integer departamentoId) {
        return departamentoService.listarDepartamentoId(departamentoId)
                .map(departamento -> new ResponseEntity<>(departamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Criar novos Departamentos")
    @PostMapping
    public ResponseEntity<Departamento> criarDepartamento(@RequestBody Departamento novoDepartamento) {
        Departamento createdDepartamento = departamentoService.criarDepartamento(novoDepartamento);
        return new ResponseEntity<>(createdDepartamento, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar os dados do Departamento por meio do seu id")
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizarDepartamento(
            @PathVariable Integer id,
            @RequestBody Departamento updatedDepartamento
    ) {
        Departamento atualizado = departamentoService.atualizarDepartamento(id, updatedDepartamento);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @Operation(summary = "Deleta o Departamento po meuo do seu id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepartamento(@PathVariable Integer id) {
        departamentoService.deletarDepartamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
