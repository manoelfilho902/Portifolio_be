/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.repository;

import com.github.manoelfilho902.Portifolio_be.model.entity.Client;
import com.github.manoelfilho902.Portifolio_be.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Repository
public interface ClientRepository extends BaseRepository<Client, Long>{
    
}
