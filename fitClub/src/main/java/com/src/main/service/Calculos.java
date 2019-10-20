package com.src.main.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.src.main.entities.Aluno;
import com.src.main.entities.Pagamento;

public class Calculos {

	public static void calculoPagameto (Pagamento pagamento, Aluno aluno) {

		Calendar calendar = Calendar.getInstance();

		if(aluno.getDataProximoPagamento() != null
				&& aluno.getDataProximoPagamento().compareTo(calendar.getTime()) > 0 ) {
			calendar.setTime(aluno.getDataProximoPagamento());
		}

		calendar.add(Calendar.MONTH, 1);
		Date novaDataProximoPagamento = calendar.getTime();
		aluno.setDataProximoPagamento(novaDataProximoPagamento);

	}

	public static void calculoFerias (Pagamento pagamento, Aluno aluno) {

		Calendar calendar = Calendar.getInstance();

		if(aluno.getDataProximoPagamento() != null
				&& aluno.getDataProximoPagamento().compareTo(calendar.getTime()) > 0 ) {
			calendar.setTime(aluno.getDataProximoPagamento());
		}

		calendar.add(Calendar.DATE, pagamento.getValor().intValue());
		Date novaDataProximoPagamento = calendar.getTime();
		aluno.setDataProximoPagamento(novaDataProximoPagamento);

	}

	public static boolean qtddPeriodosFerias (List<Pagamento> lista) {

		if (lista.size()==3) {
			return true;
		}
		return false;
	}

	public static boolean qtddDiasFerias (List<Pagamento> lista, Double qtd) {

		Double dias = 0.0D;

		for (Pagamento pgt : lista) {
			dias += pgt.getValor();
		}

		dias += qtd;

		if (dias > 30.0D) {
			return true;
		}

		return false;
	}

}
