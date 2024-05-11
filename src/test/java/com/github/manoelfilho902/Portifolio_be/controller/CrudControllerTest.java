/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller;

import com.github.manoelfilho902.Portifolio_be.model.entity.Transaction;
import com.github.manoelfilho902.Portifolio_be.model.support.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudControllerTest {
    
    @Autowired
    private TestRestTemplate template;
    @Autowired
    @Qualifier(value = "AdminPass")
    private String adminPass;
    
    private String token;
    private Transaction transaction;
    
    public CrudControllerTest() {
    }
    
    @Test
    @Order(1)
    public void authenticationTest() {
        ResponseEntity<String> postForEntity = template.postForEntity("/auth/login", new Login("admin", adminPass), String.class);
        
        Assertions.assertNotNull(postForEntity);
        Assertions.assertEquals(postForEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(postForEntity.getBody());
        Assertions.assertTrue(!postForEntity.getBody().isEmpty());
        
        token = postForEntity.getBody();
    }
    
    @Test
    @Order(2)   
    public void saveTest() {
        
    }   
    
    @Test
    @Order(3)   
    public void findByExampleTest() {
        
    }  

    
    @Test
    @Order(4)
    public void deleteTest() {
        
    }
    
}
