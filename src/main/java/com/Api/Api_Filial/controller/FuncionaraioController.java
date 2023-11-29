package com.Api.Api_Filial.controller;

import com.Api.Api_Filial.exception.FuncionarioNotFoundException;
import com.Api.Api_Filial.model.Funcionario;
import com.Api.Api_Filial.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiais")
public class FuncionaraioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Listar todos os funcionários")
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionariosFilial(){
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @Operation(summary = "Pelo id da Filial vai listar todos os Funcionários que trabalham nessa Filial")
    @GetMapping("/{filialId}/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionariosFilialId(@PathVariable Integer filialId){
        List<Funcionario> funcionarios = funcionarioService.listarFuncionariosByFilialId(filialId);
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @Operation(summary = "Passando o id da Filial e em seguida o id do Funcionário, vai mostrar quem é esse Funcionário")
    @GetMapping("/{filialId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Funcionario> getFuncionarioById(
            @PathVariable("filialId") Integer funcionarioId,
            @PathVariable("funcionarioId") Integer filialId
    ) {
        return funcionarioService.listarFuncionariosFilialId(filialId, funcionarioId)
                .map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria um Funcionário diretamente na Filial e no Departamento que ele irá trabalhar")
    @PostMapping("/{filialId}/{departamentoId}/funcionarios")
    public ResponseEntity<Funcionario> criarFuncionario(
            @PathVariable("filialId") Integer filialId,
            @PathVariable("departamentoId") Integer departamentoId,
            @RequestBody Funcionario novoFuncionario
    ) {
        Funcionario createdFuncionario = funcionarioService.criarFuncionario(filialId, departamentoId,novoFuncionario);
        return new ResponseEntity<>(createdFuncionario, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza todos os dados do Funcionário passando seu id da Filial e o do Funcionário")
    @PutMapping("/{filialId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
            @PathVariable Integer filialId,
            @PathVariable Integer funcionarioId,
            @RequestBody Funcionario atualizadoFuncionario
    )  throws FuncionarioNotFoundException {
        Funcionario atualizar = funcionarioService.atualizarFuncionario(filialId, funcionarioId, atualizadoFuncionario);
        return new ResponseEntity<>(atualizar, HttpStatus.OK);
    }

    @Operation(summary = "Deleto o Funcionario específico passando o id da Filial e do id do Funcionário")
    @DeleteMapping("/{filialId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Void> deleteFuncionario(
            @PathVariable Integer filialId,
            @PathVariable Integer funcionarioId
    ) {
        funcionarioService.deleteFuncionario(filialId, funcionarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
