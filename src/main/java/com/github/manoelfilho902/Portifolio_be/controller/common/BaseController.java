/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller.common;

import com.github.manoelfilho902.Portifolio_be.exception.HttpErroException;
import com.github.manoelfilho902.Portifolio_be.model.entity.common.BaseEntity;
import com.github.manoelfilho902.Portifolio_be.service.common.BaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 * @param <T> Entity
 * @param <ID>
 */
public class BaseController<T extends BaseEntity, ID extends Object> {

    private final BaseService<T, ID> service;
    
    public BaseController(BaseService<T, ID> service) {
        this.service = service;
    }

    @PostMapping("/find_page_by_example")
    public Page<T> FindPageByExample(@PageableDefault(size = 50, direction = Sort.Direction.ASC, sort = "id") Pageable pageable, @RequestBody T entity) {
        if (entity == null) {
            return service.findAll(pageable);
        }
        return service.findAll(Example.of(entity), pageable);
    }

    @PostMapping("/find_page_by")
    public Page<T> FindPageByExample(@PageableDefault(size = 50, direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        return service.findAll(pageable);

    }

    @PostMapping("/save")
    public T save(@RequestBody @Valid T entity) {
        try {
            T save = service.save(entity);
            return save;
        } catch (Exception e) {
            throw new HttpErroException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody @Valid T entity) {

        try {
            service.delete(entity);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new HttpErroException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_by_id/{id}")
    public ResponseEntity<String> delete(@PathVariable(required = true) Long id) {
        if (id == null) {
            throw new HttpErroException("Entity can't be null", HttpStatus.BAD_REQUEST);
        }
        try {
            service.deleteById((ID) id);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new HttpErroException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
