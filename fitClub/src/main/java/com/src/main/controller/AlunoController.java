package com.src.main.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.src.main.entities.Aluno;
import com.src.main.repository.AlunoRepository;

@Controller    // This means that this class is a Controller

public class AlunoController {
	@Autowired // This means to get the bean called alunoRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private AlunoRepository alunoRepository;

	@GetMapping("/adicionarAluno")
	public String showSignUpForm(Aluno aluno) {
		return "adicionarAluno";
	}

	@PostMapping("/adicionarAluno")
	public String addUser(@Valid Aluno aluno, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "adicionarAluno";
		}
		
		aluno.setDataMatricula(new Date(System.currentTimeMillis()));
		aluno.setDataProximoPagamento(new Date(System.currentTimeMillis()));
		
		alunoRepository.save(aluno);
		model.addAttribute("message", aluno.getName()+" gravado com sucesso!");
		return "adicionarAluno";
	}
	
	@GetMapping("/pesquisarAluno")
	public String showSearchForm(Model model) {
		model.addAttribute("alunos", alunoRepository.findAll());
		return "pesquisarAluno";
	}
	
	@GetMapping("/alunosMatriculados")
	public String showAlunosMatriculados(Model model) {
		model.addAttribute("alunos", alunoRepository.findAll());
		return "alunosMatriculados";
	}

	@GetMapping("/editarAluno/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));

		model.addAttribute("aluno", aluno);
		return "alterarAluno";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Aluno aluno, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			aluno.setId(id);
			return "alterarAluno";
		}

		alunoRepository.save(aluno);
		model.addAttribute("alunos", alunoRepository.findAll());
		return "pesquisarAluno";
	}

	@GetMapping("/excluirAluno/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));
		alunoRepository.delete(aluno);
		model.addAttribute("alunos", alunoRepository.findAll());
		return "pesquisarAluno";
	}

}
