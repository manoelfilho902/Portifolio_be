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
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Entity(name = "role")
public class Role extends BaseEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 50)
    private RoleType type;
    @Column(nullable = false)
    private String Description;
//    @Column(columnDefinition = "Default true") ::Todo see this definition
    private Boolean active;

    public Role() {
        
    }

    public Role(RoleType type) {
        this.type = type;
    }

    public Role(RoleType type, String Description) {
        this.type = type;
        this.Description = Description;
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
        if (type != null) {
            return type.getDescription();
        }
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getAuthority() {
        return type.name();
    }

}
