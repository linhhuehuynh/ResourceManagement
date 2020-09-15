package com.example.demoresourcemanagement.controller;


import com.example.demoresourcemanagement.entity.AuthenticationResponse;
import com.example.demoresourcemanagement.entity.UserCredential;
import com.example.demoresourcemanagement.security.util.JwtUtil;
import com.example.demoresourcemanagement.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
class HelloWorldController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserCredentialService userCredentialService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String firstPage() {
        return "Welcome to Resource Management Application!";
    }


    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String returnLoginPage() {
        return "Please Login!";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredential userCredential) throws Exception {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userCredential.getUsername(), userCredential.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//
//        final UserDetails userDetails = userCredentialService
//                .loadUserByUsername(userCredential.getUsername());
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("TOKEN", jwt);
//
//        return new ResponseEntity<>(headers, HttpStatus.OK);
//    }



//ResponseEntity.ok(new AuthenticationResponse(jwt))