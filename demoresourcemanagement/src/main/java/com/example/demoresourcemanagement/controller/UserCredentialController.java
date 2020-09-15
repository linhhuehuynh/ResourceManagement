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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void addUserCredential(@RequestBody UserCredential userCredential) {
        String encodedPassword = passwordEncoder.encode(userCredential.getPassword());
        userCredential.setPassword(encodedPassword);
        userCredentialService.addUserCredential(userCredential);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredential userCredential) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredential.getUsername(), userCredential.getPassword())
            );
        }
        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
            return new ResponseEntity<>("Incorrect username or password!", HttpStatus.NOT_FOUND);
        }

        final UserDetails userDetails = userCredentialService
                .loadUserByUsername(userCredential.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("TOKEN", jwt);

        Map<String,String> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("username",userCredential.getUsername());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> redirectLoginAfterLogOut() throws IOException {
        return new ResponseEntity<>(HttpStatus.OK);
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

