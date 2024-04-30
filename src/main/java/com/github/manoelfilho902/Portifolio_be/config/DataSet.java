/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Configuration
public class DataSet {

    @Bean
    public DataSource dataSource() {
        HikariDataSource hds = new HikariDataSource();
        hds.setDriverClassName("org.h2.Driver");
        hds.setJdbcUrl("jdbc:h2:file:/data/data.db");
        hds.setUsername("user");
        hds.setPassword("teste1");
        
        return hds;
    }
}
