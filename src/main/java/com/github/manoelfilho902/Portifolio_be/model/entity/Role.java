/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.model.entity;

import com.github.manoelfilho902.Portifolio_be.model.emunerate.RoleType;
import com.github.manoelfilho902.Portifolio_be.model.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Entity
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleType type;
    private String Description;

    public Role(RoleType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getDescription() {
        if(type != null){
            return type.getDescription();
        }
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    
}
