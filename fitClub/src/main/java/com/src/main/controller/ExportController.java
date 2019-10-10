package com.src.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.src.main.repository.AlunoRepository;

@Controller
public class ExportController {

	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/download")
	public String download(Model model) {
	    model.addAttribute("alunosInadimplentes", alunoRepository.findListaInadimplentes());
	    return "";
	}
	
}
