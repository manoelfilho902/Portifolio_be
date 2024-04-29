/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.controller;

import com.github.manoelfilho902.Portifolio_be.exception.HttpErroException;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.RoleType;
import com.github.manoelfilho902.Portifolio_be.model.entity.Role;
import com.github.manoelfilho902.Portifolio_be.model.entity.User;
import com.github.manoelfilho902.Portifolio_be.model.support.Login;
import com.github.manoelfilho902.Portifolio_be.service.JwtService;
import com.github.manoelfilho902.Portifolio_be.service.RoleService;
import com.github.manoelfilho902.Portifolio_be.service.UserService;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class auth {

    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.GenerateToken(login.getUserName()));
        }

        throw new HttpErroException("Username not found!", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> teste() {
        User user = new User("login to test  1", "testmail@test.com");
        user.setPassword(passwordEncoder.encode("test@1234"));
        HashSet<Role> roles = new HashSet<>();

        for (RoleType value : RoleType.values()) {
            roles.add(roleService.save(new Role(value, value.getDescription())));
        }

        user.setRoles(roles);

        service.save(user);

        return ResponseEntity.ok("Success");
    }
}
