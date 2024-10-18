package com.example.ac2p1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2p1.dtos.DadosProjetoDTO;
import com.example.ac2p1.dtos.FuncionarioDTO;
import com.example.ac2p1.models.Funcionario;
import com.example.ac2p1.repository.FuncionarioRepository;
import com.example.ac2p1.repository.ProjetoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public void adicionar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionarioRepository.save(funcionario);
    }

    public List<DadosProjetoDTO> buscarProjetos(Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElse(null);
        if (funcionario == null)
            return null;

        return projetoRepository.findByFuncionariosId(idFuncionario).stream().map(projeto -> {
            DadosProjetoDTO dadosProjetoDTO = new DadosProjetoDTO();
            dadosProjetoDTO.setId(projeto.getId());
            dadosProjetoDTO.setDescricao(projeto.getDescricao());
            dadosProjetoDTO.setDataInicio(projeto.getDataInicio());
            dadosProjetoDTO.setDataFim(projeto.getDataFim());
            dadosProjetoDTO.setFuncionariosNomes(
                    projeto.getFuncionarios().stream().map(Funcionario::getNome).collect(Collectors.toList()));
            return dadosProjetoDTO;
        }).collect(Collectors.toList());
    }
}
