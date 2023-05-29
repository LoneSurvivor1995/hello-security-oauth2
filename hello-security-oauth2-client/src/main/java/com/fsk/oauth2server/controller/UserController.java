package com.fsk.oauth2server.controller;


import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @GetMapping("/user/getInfo")
    public Object index(Authentication authentication){
        return authentication;
    }

    @GetMapping("/user/getDetail")
    public Object getDetail(HttpServletRequest request){
        String authentication = request.getHeader("Authorization");
        String token = authentication.replace("Bearer ", "");
        return Jwts.parser().setSigningKey("jwtTest".getBytes()).parseClaimsJws(token).getBody();
    }

}
