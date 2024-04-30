/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config;

import com.github.manoelfilho902.Portifolio_be.model.emunerate.RoleType;
import com.github.manoelfilho902.Portifolio_be.model.entity.Role;
import com.github.manoelfilho902.Portifolio_be.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Component
public class AfterStart {
    @Autowired
    private RoleService rs;
    
    @PostConstruct
    public void populateRoles(){
        long count = rs.count();
        if(count == 0){
            for (RoleType value : RoleType.values()) {
                Role role = new Role(value, value.getDescription());
                role.setActive(Boolean.TRUE);
                rs.save(role);
            }
        }
    }
}
