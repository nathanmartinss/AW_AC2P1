package com.example.ac2p1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ac2p1.dtos.DadosProjetoDTO;
import com.example.ac2p1.dtos.FuncionarioDTO;
import com.example.ac2p1.services.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/adicionar")
    public void adicionarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.adicionar(funcionarioDTO);
    }

    @GetMapping("/{id}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable("id") Long idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }
}