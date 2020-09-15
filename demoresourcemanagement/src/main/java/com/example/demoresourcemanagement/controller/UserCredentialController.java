package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.UserCredential;
import com.example.demoresourcemanagement.security.util.JwtUtil;
import com.example.demoresourcemanagement.service.UserCredentialService;
import com.example.demoresourcemanagement.service.UserService;
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
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialService;

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/register")
    public void addUserCredential(@RequestBody UserCredential userCredential) {
        userCredentialService.addUserCredential(userCredential);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredential userCredential) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredential.getUsername(), userCredential.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userCredentialService
                .loadUserByUsername(userCredential.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.add("TOKEN", jwt);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/userCredential/{id}")
    @ResponseBody
    public ResponseEntity<UserCredential> getUserCredential(@PathVariable int id) {
        return userCredentialService.getUserCredential(id);
    }

    @PutMapping("/userCredential/{id}")
    public ResponseEntity<?> setUserCredential(@PathVariable int id, @RequestBody UserCredential userCredential) {
        return userCredentialService.setUserCredential(id, userCredential);
    }

    @DeleteMapping("/userCredential/{id}")
    public ResponseEntity<?> deleteUserCredentialById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
}

