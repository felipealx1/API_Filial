package com.Api.Api_Filial.service;

import com.Api.Api_Filial.exception.FilialNotFoundException;
import com.Api.Api_Filial.model.Filial;
import com.Api.Api_Filial.model.Funcionario;
import com.Api.Api_Filial.repository.FilialRepository;
import com.Api.Api_Filial.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FilialService {
    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Filial> listarFiliais(){
        return filialRepository.findAll();
    }

    public Optional<Filial> listarFilialId(Integer id){
        return filialRepository.findById(id);
    }


    public Filial criarFilial(Filial filial){
        if (filialRepository.existsByCnpj(filial.getCnpj())){
            throw new FilialNotFoundException("Já existe uma Filial com esse Cnpj");
        }

        return filialRepository.save(filial);
    }

    public Filial atualizarFilial(Integer id, Filial filialAtualizada){
        Optional<Filial> filialExistente = filialRepository.findById(id);

        if(filialExistente.isPresent()){
            Filial filial = filialExistente.get();
            filial.setCidade(filialAtualizada.getCidade());
            filial.setCnpj(filialAtualizada.getCnpj());
            filial.setEndereco(filialAtualizada.getEndereco());

            if (!filial.getCnpj().equals(filialAtualizada.getCnpj())
                    && filialRepository.existsByCnpj(filialAtualizada.getCnpj())){
                throw new FilialNotFoundException("Já existe uma Filial com o mesmo Cnpj");
            }

            return filialRepository.save(filial);

        }else {
            throw new FilialNotFoundException("Filial não encontrada pelo id: " + id);
        }
    }

    public void deletarFilial(Integer id){

        Optional<Filial> filial = filialRepository.findById(id);

        if (filial.isPresent()){
            List<Funcionario> funcionarios = funcionarioRepository.findByFilialId(id);
            funcionarios.forEach(funcionario -> funcionario.setFilial(null));
            funcionarioRepository.saveAll(funcionarios);

            filialRepository.deleteById(id);
        }else {
            throw new FilialNotFoundException("Filial não encontrada com o ID: " + id);
        }
    }
}
