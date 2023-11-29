package com.Api.Api_Filial.controller;

import com.Api.Api_Filial.model.Filial;
import com.Api.Api_Filial.service.FilialService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiais")
public class FilialController {
    @Autowired
    private FilialService filialService;

    @Operation(summary = "Listar todas as Filiais.")
    @GetMapping
    public ResponseEntity<List<Filial>> exibirFiliais(){
        List<Filial> filiais = filialService.listarFiliais();
        return new ResponseEntity<>(filiais, HttpStatus.OK);
    }

    @Operation(summary = "Lista a Filial pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<Filial> exibirFilialId(@PathVariable Integer id){
        return filialService.listarFilialId(id).
                map(filial -> new ResponseEntity<>(filial, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Cria novas Filiais")
    @PostMapping
    public ResponseEntity<Filial> adicionarFilial(@RequestBody Filial filial){
        Filial criarFilial = filialService.criarFilial(filial);
        return new ResponseEntity<>(criarFilial, HttpStatus.CREATED);
    }

    @Operation(summary = "Vai atualizar os dados da Filial por meio do id")
    @PutMapping("/{id}")
    public ResponseEntity<Filial> atulaizarFilial(@PathVariable Integer id,
                                                    @RequestBody Filial filialAtualizada){
        Filial atualizar = filialService.atualizarFilial(id, filialAtualizada);
        return new ResponseEntity<>(atualizar, HttpStatus.OK);
    }

    @Operation(summary = "Deleta a Filial pelo seu id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFilial(@PathVariable Integer id){
        filialService.deletarFilial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
