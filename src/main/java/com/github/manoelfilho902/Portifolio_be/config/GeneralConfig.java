/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import de.svenjacobs.loremipsum.LoremIpsum;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Configuration
public class GeneralConfig {
    @Bean
    public ObjectMapper mapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        
        JavaTimeModule timeM = new JavaTimeModule();
        SpringDataJacksonConfiguration.PageModule pageModule = new SpringDataJacksonConfiguration.PageModule();
        Hibernate6Module hibernate6Module = new Hibernate6Module();
        
        objectMapper.registerModule(timeM);
        objectMapper.registerModule(pageModule);
        objectMapper.registerModule(hibernate6Module);
        
        return objectMapper;
    }
    
    @Bean
    public LoremIpsum loremIpsum(){
        return new LoremIpsum();
    }
}
