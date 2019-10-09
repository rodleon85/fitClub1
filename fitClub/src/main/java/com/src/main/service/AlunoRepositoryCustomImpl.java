package com.src.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.src.main.entities.Aluno;
import com.src.main.repository.AlunoRepositoryCustom;

public class AlunoRepositoryCustomImpl implements AlunoRepositoryCustom {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    @Override
    public List<Aluno> findAlunoByNameContains(String nome) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Aluno> query = cb.createQuery(Aluno.class);
        Root<Aluno> aluno = query.from(Aluno.class);
 
        Path<String> namePath = aluno.get("name");
 
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(namePath, nome));
        query.select(aluno).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
 
        return entityManager.createQuery(query)
            .getResultList();
    }

}