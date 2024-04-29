/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.model.emunerate;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
public enum RoleType {
    ADMINISTRATOR("One of sistem admintrators"), USER("A simple user of my sistem with limited access");
 
    private final String description;

    private RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    
}
