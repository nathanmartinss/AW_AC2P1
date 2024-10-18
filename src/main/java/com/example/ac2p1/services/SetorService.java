package com.example.ac2p1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2p1.dtos.DadosSetorDTO;
import com.example.ac2p1.dtos.SetorDTO;
import com.example.ac2p1.models.Funcionario;
import com.example.ac2p1.models.Setor;
import com.example.ac2p1.repository.SetorRepository;

import java.util.stream.Collectors;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    public void adicionar(SetorDTO setorDTO) {
        Setor setor = new Setor();
        setor.setNome(setorDTO.getNome());
        setorRepository.save(setor);
    }

    public DadosSetorDTO buscarSetorPorId(Integer idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElse(null);
        if (setor == null)
            return null;

        DadosSetorDTO dadosSetorDTO = new DadosSetorDTO();
        dadosSetorDTO.setId(setor.getId());
        dadosSetorDTO.setNome(setor.getNome());
        dadosSetorDTO.setFuncionariosNomes(
                setor.getFuncionarios().stream().map(Funcionario::getNome).collect(Collectors.toList()));

        return dadosSetorDTO;
    }
}