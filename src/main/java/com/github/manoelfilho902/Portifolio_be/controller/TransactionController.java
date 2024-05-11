/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller;

import com.github.manoelfilho902.Portifolio_be.controller.common.BaseController;
import com.github.manoelfilho902.Portifolio_be.model.entity.Transaction;
import com.github.manoelfilho902.Portifolio_be.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController extends BaseController<Transaction, Long>{
    @Autowired
    private TransactionService service;

    public TransactionController(TransactionService service) {
        super(service);
        this.service = service;
    }
    
}
