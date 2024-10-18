package com.example.ac2p1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2p1.dtos.DadosProjetoDTO;
import com.example.ac2p1.dtos.ProjetoDTO;
import com.example.ac2p1.models.Funcionario;
import com.example.ac2p1.models.Projeto;
import com.example.ac2p1.repository.FuncionarioRepository;
import com.example.ac2p1.repository.ProjetoRepository;

import java.util.stream.Collectors;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void adicionar(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setFuncionarios(projetoDTO.getFuncionariosIds().stream()
                .map(id -> funcionarioRepository.findById(id).orElse(null)).collect(Collectors.toList()));
        projetoRepository.save(projeto);
    }

    public DadosProjetoDTO buscarProjetoPorId(Long id) {
        Projeto projeto = projetoRepository.findById(id).orElse(null);
        if (projeto == null)
            return null;

        DadosProjetoDTO dadosProjetoDTO = new DadosProjetoDTO();
        dadosProjetoDTO.setId(projeto.getId());
        dadosProjetoDTO.setDescricao(projeto.getDescricao());
        dadosProjetoDTO.setDataInicio(projeto.getDataInicio());
        dadosProjetoDTO.setDataFim(projeto.getDataFim());
        dadosProjetoDTO.setFuncionariosNomes(
                projeto.getFuncionarios().stream().map(Funcionario::getNome).collect(Collectors.toList()));

        return dadosProjetoDTO;
    }

    public void vincularFuncionario(Long idProjeto, Long idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto).orElse(null);
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElse(null);

        if (projeto != null && funcionario != null) {
            projeto.getFuncionarios().add(funcionario);
            projetoRepository.save(projeto);
        }
    }
}
