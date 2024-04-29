/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.service.common;

import com.github.manoelfilho902.Portifolio_be.model.entity.common.BaseEntity;
import com.github.manoelfilho902.Portifolio_be.repository.common.BaseRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 * @param <T> Entity class
 * @param <ID> id Class
 */
public abstract class BaseService<T extends BaseEntity, ID extends Object> {

    private final BaseRepository<T, ID> repository;

    public BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }

    public List<T> findAllById(List<ID> ids) {
        return repository.findAllById(ids);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public long count() {
        return repository.count();
    }

    public long count(Example<T> entity) {
        return repository.count(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<T> findOne(Example<T> example) {
        return repository.findOne(example);
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    public boolean exists(Example<T> example) {
        return repository.exists(example);
    }

    /**
     * @param <R> Return
     * @param example
     * @param queryFunction
     * @return
     */
    public abstract <R> R findBy(Example<T> example, Function<FluentQuery.FetchableFluentQuery<T>, R> queryFunction);

}
