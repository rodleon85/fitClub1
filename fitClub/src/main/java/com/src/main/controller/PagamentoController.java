package com.src.main.controller;

import java.util.Calendar;
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
import com.src.main.entities.Pagamento;
import com.src.main.repository.AlunoRepository;
import com.src.main.repository.PagamentoRepository;

@Controller
public class PagamentoController {
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/resumoPagamentos/{id}")
	public String showPayInfo(@PathVariable("id") long id, Model model) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));

		model.addAttribute("aluno", aluno);
		
		return "resumoPagamentos";
	}
	
	@GetMapping("/adicionarPagamento/{idAluno}")
	public String showSignUpForm(@PathVariable("idAluno") long idAluno, Pagamento pagamento, Model model) {
		Aluno aluno = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));

		model.addAttribute("aluno", aluno);
		
		return "adicionarPagamento";
	}

	@PostMapping("/adicionarPagamento/{idAluno}")
	public String addPagamento(@PathVariable("idAluno") long idAluno, @Valid Pagamento pagamento, 
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "adicionarPagamento";
		}
		
		Aluno aluno = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));
		
		pagamento.setDataPagamento(new Date());
		pagamento.setAluno(aluno);
		
		
		if(pagamento.getTipoPagamento().equals("mensal")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pagamento.getDataPagamento());
			calendar.add(Calendar.MONTH, 1);
			Date novaDataProximoPagamento = calendar.getTime();
			aluno.setDataProximoPagamento(novaDataProximoPagamento);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pagamento.getDataPagamento());
			calendar.add(Calendar.DATE, pagamento.getValor().intValue());
			Date novaDataProximoPagamento = calendar.getTime();
			aluno.setDataProximoPagamento(novaDataProximoPagamento);
		}
		
		if (result.hasErrors()) {
			return "adicionarPagamento";
		}
		
		
//		aluno.getPagamentos().add(pagamento);
		
		pagamentoRepository.save(pagamento);
		
		model.addAttribute("aluno", aluno);
		return "resumoPagamentos";
	}
	
	@GetMapping("/alunosInadimplentes")
	public String showAlunosInadimplentes(Model model) {
		model.addAttribute("alunos", alunoRepository.findListaInadimplentes());
		return "alunosInadimplentes";
	}
	
}
