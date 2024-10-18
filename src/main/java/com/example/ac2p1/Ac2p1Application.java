package com.example.ac2p1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac2p1.models.Funcionario;
import com.example.ac2p1.models.Projeto;
import com.example.ac2p1.models.Setor;
import com.example.ac2p1.repository.FuncionarioRepository;
import com.example.ac2p1.repository.ProjetoRepository;
import com.example.ac2p1.repository.SetorRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Ac2p1Application {
	public static void main(String[] args) {
		SpringApplication.run(Ac2p1Application.class, args);
	}

	@Bean
	CommandLineRunner initData(FuncionarioRepository funcionarioRepository, ProjetoRepository projetoRepository,
			SetorRepository setorRepository) {
		return args -> {

			Setor setor1 = Setor.builder().nome("Desenvolvimento").build();
			Setor setor2 = Setor.builder().nome("Recursos Humanos").build();
			Setor setor3 = Setor.builder().nome("Financeiro").build();
			setorRepository.saveAll(List.of(setor1, setor2, setor3));

			Funcionario func1 = Funcionario.builder().nome("João").setor(setor1).build();
			Funcionario func2 = Funcionario.builder().nome("Maria").setor(setor2).build();
			Funcionario func3 = Funcionario.builder().nome("Carlos").setor(setor3).build();
			funcionarioRepository.saveAll(List.of(func1, func2, func3));

			Projeto projeto1 = Projeto.builder()
					.descricao("Projeto Alpha")
					.dataInicio(LocalDate.now())
					.dataFim(LocalDate.now().plusMonths(6))
					.funcionarios(List.of(func1, func2))
					.build();

			Projeto projeto2 = Projeto.builder()
					.descricao("Projeto Beta")
					.dataInicio(LocalDate.now())
					.dataFim(LocalDate.now().plusMonths(12))
					.funcionarios(List.of(func2, func3))
					.build();

			Projeto projeto3 = Projeto.builder()
					.descricao("Projeto Gamma")
					.dataInicio(LocalDate.now())
					.dataFim(LocalDate.now().plusMonths(3))
					.funcionarios(List.of(func1, func3))
					.build();

			projetoRepository.saveAll(List.of(projeto1, projeto2, projeto3));
		};
	}
}