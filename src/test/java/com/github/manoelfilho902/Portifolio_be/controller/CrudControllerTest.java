/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller;

import com.github.manoelfilho902.Portifolio_be.model.entity.Transaction;
import com.github.manoelfilho902.Portifolio_be.model.support.CustomPageImpl;
import com.github.manoelfilho902.Portifolio_be.model.support.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
    private static final Logger LOG = Logger.getLogger(CrudControllerTest.class.getName());

    private final HttpHeaders headers = new HttpHeaders();
    private Transaction transaction;
    private final String TEST_DESCRIPTION = "THIS IS A DESCRIPTION FOR TESTS";

    public CrudControllerTest() {
    }

    @BeforeEach
    @Test
    @Order(1)
    public void authenticationTest() {
        ResponseEntity<String> authenticatioRequest = template.postForEntity("/auth/login", new Login("admin@myaplication.com", adminPass), String.class);

        Assertions.assertNotNull(authenticatioRequest);
        Assertions.assertEquals(HttpStatus.OK, authenticatioRequest.getStatusCode());
        Assertions.assertNotNull(authenticatioRequest.getBody());
        Assertions.assertTrue(!authenticatioRequest.getBody().isEmpty());

        headers.set("Authorization", "Bearer ".concat(authenticatioRequest.getBody()));

        ResponseEntity<CustomPageImpl<Transaction>> authTestRequest = template.exchange("/transaction/get_page?size=1", HttpMethod.GET, new HttpEntity<>("parameters", headers), new ParameterizedTypeReference<CustomPageImpl<Transaction>>() {
        });

        Assertions.assertNotNull(authTestRequest);
        Assertions.assertEquals(HttpStatus.OK, authTestRequest.getStatusCode());
        if (authTestRequest.hasBody()) {
            if (authTestRequest.getBody().hasContent()) {
                transaction = authTestRequest.getBody().getContent().get(0);
            }
        }

    }

    @Test
    @Order(2)
    public void saveTest() {

        Assertions.assertNotNull(transaction);
        transaction.setId(null);
        transaction.setDescription(TEST_DESCRIPTION);

        ResponseEntity<Transaction> saveRequest = template.exchange("/transaction/save", HttpMethod.POST, new HttpEntity<>(transaction, headers), Transaction.class);

        Assertions.assertNotNull(saveRequest);
        Assertions.assertEquals(HttpStatus.OK, saveRequest.getStatusCode());

        Assertions.assertNotNull(saveRequest.getBody());
        
        transaction = saveRequest.getBody();
    }

    @Test
    @Order(3)
    public void findByExampleTest() {
        Transaction t = new Transaction();
        t.setDescription(TEST_DESCRIPTION);

        ResponseEntity<CustomPageImpl<Transaction>> findRequest = template.exchange("/transaction/find_page_by_example", HttpMethod.POST, new HttpEntity<>(t, headers), new ParameterizedTypeReference<CustomPageImpl<Transaction>>() {
        });

        Assertions.assertNotNull(findRequest);
        Assertions.assertEquals(HttpStatus.OK, findRequest.getStatusCode());
        Assertions.assertNotNull(findRequest.getBody());
        Assertions.assertTrue(findRequest.getBody().hasContent());
    }

    @Test
    @Order(4)
    public void deleteTest() {
        Transaction t = new Transaction();
        t.setDescription(TEST_DESCRIPTION);

        ResponseEntity<CustomPageImpl<Transaction>> findRequest = template.exchange("/transaction/find_page_by_example", HttpMethod.POST, new HttpEntity<>(t, headers), new ParameterizedTypeReference<CustomPageImpl<Transaction>>() {
        });

        Assertions.assertNotNull(findRequest);
        Assertions.assertEquals(HttpStatus.OK, findRequest.getStatusCode());
        Assertions.assertNotNull(findRequest.getBody());
        Assertions.assertTrue(findRequest.getBody().hasContent());

        ResponseEntity deleteRequest = template.exchange("/transaction/delete", HttpMethod.DELETE, new HttpEntity<>(t, headers), String.class);
        
        Assertions.assertEquals(HttpStatus.OK, deleteRequest.getStatusCode());
    }

}
