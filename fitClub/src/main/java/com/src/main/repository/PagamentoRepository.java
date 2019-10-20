package com.src.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.src.main.entities.Pagamento;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
	
	@Query(value = "select id, data_pagamento, tipo_pagamento, valor, aluno_id from pagamento where tipo_pagamento = 'ferias' and aluno_id = ?1 and YEAR(data_pagamento) = YEAR(CURDATE())"
    		,nativeQuery = true)
	List<Pagamento> listaFeriasAno(long aluno_id);
	
}