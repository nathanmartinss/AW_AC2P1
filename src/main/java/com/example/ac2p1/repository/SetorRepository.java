package com.example.ac2p1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ac2p1.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
    // Nenhum método customizado necessário
}