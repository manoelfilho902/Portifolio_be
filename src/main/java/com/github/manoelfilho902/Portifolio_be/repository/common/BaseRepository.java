/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.repository.common;

import com.github.manoelfilho902.Portifolio_be.model.entity.common.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Um super repositorio com diversas funções crud ja implementadas. Observe que
 * não é usado org.springframework.data.jpa.repository.JpaRepository isso ocorre
 * por que trabalhando com springo data JDBC não existe essa interface logo para
 * reaproveitamento essa interface utiliza da mesta herança da de
 * org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 * @param <T> Entity class
 * @param <ID> id Class
 *
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Object> extends ListCrudRepository<T, ID>, ListPagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>, CrudRepository<T, ID> {

}
