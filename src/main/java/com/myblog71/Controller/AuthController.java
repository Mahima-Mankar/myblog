package com.myblog71.Controller;

import com.myblog71.Entity.Role;
import com.myblog71.Entity.User;
import com.myblog71.Payload.LoginDto;
import com.myblog71.Payload.SignUpDto;
import com.myblog71.Repository.RoleRepository;
import com.myblog71.Repository.UserRepository;
import com.myblog71.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired

    private JwtTokenProvider tokenProvider;

    //http://localhost:8080/api/auth/signin
    @PostMapping("/signin")

    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));



        SecurityContextHolder.getContext().setAuthentication(authentication);


// get token form tokenProvider

        String token = tokenProvider.generateToken(authentication);


        return ResponseEntity.ok(new JWTAuthResponse(token));

    }





    //http://localhost:8080/api/auth/signup
    @PostMapping("/signup")

    public ResponseEntity<String > registerUser(@RequestBody SignUpDto signUpDto){

        Boolean emailExist = userRepository.existsByEmail(signUpDto.getEmail());

        if(emailExist){
            return new ResponseEntity<>("Email id exist",HttpStatus.BAD_REQUEST);
        }
        Boolean usernameExist = userRepository.existsByUsername(signUpDto.getUsername());
        if(usernameExist){
            return new ResponseEntity<>("username exist",HttpStatus.BAD_REQUEST);
        }


        User user = new User();
       user.setName(signUpDto.getName());
       user.setUsername(signUpDto.getUsername());
       user.setEmail(signUpDto.getEmail());
       user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

       Role roles = roleRepository.findByName("ROLE_ADMIN").get();
       user.setRoles(Collections.singleton(roles));
       userRepository.save(user);
       return new ResponseEntity<>("User is Registered",HttpStatus.CREATED);

    }
}






