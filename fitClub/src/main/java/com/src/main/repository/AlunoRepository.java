package com.src.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.src.main.entities.Aluno;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AlunoRepository extends CrudRepository<Aluno, Long>, AlunoRepositoryCustom {
    @Query(value = "select id, name, cpf, telefone, rg, endereco, email, plano, data_matricula, data_proximo_pagamento, "
    		+ "'INADIMPLENTE' as 'status' from aluno where data_proximo_pagamento < CURDATE() "
    		+ "or data_proximo_pagamento is null"
    		,nativeQuery = true)
	List<Aluno> findListaInadimplentes();
}