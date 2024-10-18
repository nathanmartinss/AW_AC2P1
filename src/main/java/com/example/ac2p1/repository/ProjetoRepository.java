package com.example.ac2p1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ac2p1.models.Projeto;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByDataInicioBetween(LocalDate startDate, LocalDate endDate);

    List<Projeto> findByFuncionariosId(Long funcionarioId);
}