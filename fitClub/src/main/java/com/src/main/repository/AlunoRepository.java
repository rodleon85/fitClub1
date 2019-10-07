package com.src.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.src.main.entities.Aluno;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AlunoRepository extends CrudRepository<Aluno, Long>, AlunoRepositoryCustom {
	
}