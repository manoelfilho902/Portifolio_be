/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config.filter;

import com.github.manoelfilho902.Portifolio_be.service.JwtService;
import com.github.manoelfilho902.Portifolio_be.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Component
public class JwtAthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService service;
    @Autowired
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(JwtAthenticationFilter.class.getName());
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String token = authHeader.replace("Bearer ", "");
            String userName = service.getUserfromToken(token);
            
            UserDetails ud = userService.loadUserByUsername(userName);
            
            if(service.validateToken(token, ud)){
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(upat);
                
//                LOG.log(Level.INFO, "user: {0}", ud.getUsername());
//                LOG.log(Level.INFO, "result: {0}", Boolean.toString(context.getAuthentication().isAuthenticated()));
            }

        }        
        filterChain.doFilter(request, response);
    }

}
