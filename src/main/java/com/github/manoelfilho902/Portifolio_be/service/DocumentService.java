/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.service;

import com.github.manoelfilho902.Portifolio_be.model.entity.Document;
import com.github.manoelfilho902.Portifolio_be.repository.DocumentRepository;
import com.github.manoelfilho902.Portifolio_be.service.common.BaseService;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Service
public class DocumentService extends BaseService<Document, Long> {

    @Autowired
    private DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public <R> R findBy(Example<Document> example, Function<FluentQuery.FetchableFluentQuery<Document>, R> queryFunction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
