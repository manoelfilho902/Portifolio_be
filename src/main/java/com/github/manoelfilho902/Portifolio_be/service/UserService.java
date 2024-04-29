/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.manoelfilho902.Portifolio_be.exception.HttpErroException;
import com.github.manoelfilho902.Portifolio_be.model.entity.User;
import com.github.manoelfilho902.Portifolio_be.model.support.UserDetailsImpl;
import com.github.manoelfilho902.Portifolio_be.repository.UserRepository;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findOne = repository.findOne(Example.of(new User(username, username), ExampleMatcher.matchingAny().withIgnoreCase()));
        if (findOne.isEmpty()) {
            throw new HttpErroException("user: ".concat(username).concat(" Not Found"), HttpStatus.NO_CONTENT);
        }
     
        return new UserDetailsImpl(findOne.get());
    }

    public User save(User u) {
        return repository.save(u);
    }

}
