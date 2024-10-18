package com.example.ac2p1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ac2p1.dtos.DadosSetorDTO;
import com.example.ac2p1.dtos.SetorDTO;
import com.example.ac2p1.services.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {
    @Autowired
    private SetorService setorService;

    @PostMapping("/adicionar")
    public void adicionarSetor(@RequestBody SetorDTO setorDTO) {
        setorService.adicionar(setorDTO);
    }

    @GetMapping("/{id}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable("id") Integer idSetor) {
        return setorService.buscarSetorPorId(idSetor);
    }
}
