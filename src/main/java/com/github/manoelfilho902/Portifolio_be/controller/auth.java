/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller;

import com.github.manoelfilho902.Portifolio_be.model.support.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class auth {
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        return ResponseEntity.ok("Success");
    }
    
}
