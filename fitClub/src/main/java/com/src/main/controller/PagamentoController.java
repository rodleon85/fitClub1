package com.src.main.controller;

import java.util.Date;
import java.util.List;

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
import com.src.main.service.Calculos;

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

		if(aluno.getDataProximoPagamento() == null
				|| aluno.getDataProximoPagamento().compareTo(new Date()) < 0 ) {
			aluno.setStatus("INADIMPLENTE");
		} else {
			aluno.setStatus("ADIMPLENTE");
		}
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
		
		Aluno aluno = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));
		
		model.addAttribute("aluno", aluno);
		
		if (result.hasErrors()) {
			
			return "adicionarPagamento";
		}
		
		if (pagamento.getTipoPagamento().equals("ferias")
				&& aluno.getPlano().equals("mensal")) {

			model.addAttribute("avisoMsg", "Aluno de Plano Mensal Não Pode Cadastrar Período de Férias.");

			return "adicionarPagamento";
		}
		
		if (pagamento.getValor() == null 
				|| pagamento.getValor() <= 0.0D) {
			
			if(pagamento.getTipoPagamento().equals("mensal")) {
				model.addAttribute("avisoMsg", "Valor da Mensalidade Inválido.");
			} else {
				model.addAttribute("avisoMsg", "Quantidade de Dias de Férias Inválido.");
			}
			
			return "adicionarPagamento";
		}
		
		pagamento.setDataPagamento(new Date());
		pagamento.setAluno(aluno);
		
		
		if(pagamento.getTipoPagamento().equals("mensal")) {
			Calculos.calculoPagameto(pagamento, aluno);
		} else {
			
			List<Pagamento> listFerias = pagamentoRepository.listaFeriasAno(aluno.getId());
			if(listFerias!=null) {
				if(Calculos.qtddPeriodosFerias(listFerias)) {
					model.addAttribute("avisoMsg", "Quantidade de Períodos de Férias Excedido.");
					return "adicionarPagamento";
				}
				
				if(Calculos.qtddDiasFerias(listFerias, pagamento.getValor())) {
					model.addAttribute("avisoMsg", "Quantidade de Dias de Férias Máximo Excedido.");
					return "adicionarPagamento";
				}
			}
			
			Calculos.calculoFerias(pagamento, aluno);
		}
		
//		aluno.getPagamentos().add(pagamento);
		
		pagamentoRepository.save(pagamento);
		
		if(aluno.getDataProximoPagamento() == null
				|| aluno.getDataProximoPagamento().compareTo(new Date()) < 0 ) {
			aluno.setStatus("INADIMPLENTE");
		} else {
			aluno.setStatus("ADIMPLENTE");
		}
		
		model.addAttribute("aluno", aluno);
		return "resumoPagamentos";
	}
	
	@GetMapping("/editarPagamento/{id}")
	public String showEditPayForm(@PathVariable("id") long id, Model model) {
		Pagamento pagamento = pagamentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pagamento Não Encontrado"));
		Aluno aluno = alunoRepository.findById(pagamento.getAluno().getId())
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));
		
		model.addAttribute("pagamento", pagamento);
		model.addAttribute("aluno", aluno);
		
		return "editarPagamento";
	}
	
	@PostMapping("/editarPagamento/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Pagamento pagamento, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "editarPagamento";
		}
		Pagamento pagamentoBd = pagamentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pagamento Não Encontrado"));
		
		pagamentoBd.setValor(pagamento.getValor());
		
		pagamentoRepository.save(pagamentoBd);
		Aluno aluno = alunoRepository.findById(pagamentoBd.getAluno().getId())
				.orElseThrow(() -> new IllegalArgumentException("Aluno Não Encontrado"));
		model.addAttribute("aluno", aluno);
		return "resumoPagamentos";
	}
	
	@GetMapping("/alunosInadimplentes")
	public String showAlunosInadimplentes(Model model) {
		model.addAttribute("alunos", alunoRepository.findListaInadimplentes());
		return "alunosInadimplentes";
	}
	
}
