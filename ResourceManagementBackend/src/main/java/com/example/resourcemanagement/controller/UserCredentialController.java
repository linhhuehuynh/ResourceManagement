package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.User;
import com.example.resourcemanagement.entity.UserCredential;
import com.example.resourcemanagement.security.util.JwtUtil;
import com.example.resourcemanagement.service.UserCredentialService;
import com.example.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
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
    public ResponseEntity<?> addUserCredential(@RequestBody UserCredential userCredential) {
        
        //DOUBLE CHECK
        String encodedPassword = passwordEncoder.encode(userCredential.getPassword());
        userCredential.setPassword(encodedPassword);
        Optional<UserCredential> createdUserCredential = userCredentialService.addUserCredential(userCredential);
        
        if (createdUserCredential.isPresent()) {
            return new ResponseEntity<UserCredential>(createdUserCredential.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userCredential.getUsername() + " Has Been Registered!", HttpStatus.BAD_REQUEST);
        }
    }

    //@PostMapping
    @GetMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody(required=false)  UserCredential userCredential) throws Exception {
        if (userCredential == null) {return new ResponseEntity<>("Please sign in!", HttpStatus.OK);}

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

        // HttpHeaders headers = new HttpHeaders();
        // headers.add("TOKEN", jwt);

        Map<String,String> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("username",userCredential.getUsername());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

//        @GetMapping("/login")
//    public ResponseEntity<?> redirectLoginAfterLogOut() throws IOException {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/userCredential/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserCredential(@PathVariable int id) {
        Optional<UserCredential> existUserCredential =  userCredentialService.getUserCredential(id);
        if(existUserCredential.isPresent()) {
            return new ResponseEntity<>(existUserCredential.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/userCredential/{id}")
    public ResponseEntity<?> setUserCredential(@PathVariable int id, @RequestBody UserCredential userCredential) {
        Optional<UserCredential> existUserCredential = userCredentialService.setUserCredential(id, userCredential);
        if (existUserCredential == null) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        } else if(existUserCredential.isPresent()) {
            return new ResponseEntity<>("Update User Credential Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username Has been Used, Please Use Another Username!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/userCredential/{id}")
    public ResponseEntity<?> deleteUserCredentialById(@PathVariable int id) {
        Optional<User> existUser = userService.deleteUserById(id);
        if(existUser.isPresent()) {
            return new ResponseEntity<>("Deleted User Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }
}

