package com.example.ac2p1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ac2p1.dtos.DadosProjetoDTO;
import com.example.ac2p1.dtos.ProjetoDTO;
import com.example.ac2p1.services.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/adicionar")
    public void adicionarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        projetoService.adicionar(projetoDTO);
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("/{idProjeto}/vincular/{idFuncionario}")
    public void vincularFuncionario(@PathVariable Long idProjeto, @PathVariable Long idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }
}