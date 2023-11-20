package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.Refeicao;

import java.util.List;

public interface Service<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public T persiste(T t);

}
