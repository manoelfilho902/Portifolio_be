/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.model.support;

import com.github.manoelfilho902.Portifolio_be.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
public class UserDetailsImpl extends User implements UserDetails{

    public UserDetailsImpl(User u) {
        
        this.setEmail(u.getEmail());
        this.setId(u.getId());
        this.setPassword(u.getPassword());
        this.setRoles(u.getRoles());
        this.setUserName(u.getUserName());
        
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
