package com.src.main.repository;

import java.util.List;

import com.src.main.entities.Aluno;

public interface AlunoRepositoryCustom {
    List<Aluno> findAlunoByNameContains(String name);

}