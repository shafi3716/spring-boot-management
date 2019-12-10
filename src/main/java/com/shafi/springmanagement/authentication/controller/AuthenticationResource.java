package com.shafi.springmanagement.authentication.controller;

import com.shafi.springmanagement.authentication.model.AuthenticationRequest;
import com.shafi.springmanagement.authentication.model.AuthenticationResponse;
import com.shafi.springmanagement.authentication.model.User;
import com.shafi.springmanagement.authentication.repository.UserRepository;
import com.shafi.springmanagement.authentication.service.MyUserDetails;
import com.shafi.springmanagement.authentication.util.JwtUtil;
import com.shafi.springmanagement.helper.error.DBOperationException;
import com.shafi.springmanagement.helper.response.ErrorResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationResource {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResource(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "login api", notes = "User can login using valid credentials. Default username = 'shafi' and password = '123456' required.", response = AuthenticationRequest.class)
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authorities));
//        }
//        catch (BadCredentialsException ex){
//            return new ResponseEntity(new ErrorResponse(new DBOperationException("Invalid credentials.")), HttpStatus.BAD_REQUEST);
//        }
//        final MyUserDetails userDetails  = (MyUserDetails) myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Optional<User> user;
        try {
            user = userRepository.findByUserName(authenticationRequest.getUsername());
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ErrorResponse(new DBOperationException()), HttpStatus.BAD_REQUEST);
        }

        if(!user.isPresent()){
            return new ResponseEntity<>(new ErrorResponse(new DBOperationException("Invalid credentials.")), HttpStatus.BAD_REQUEST);
        }

        if(!passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword())){
            return new ResponseEntity<>(new ErrorResponse(new DBOperationException("Password does not match.")), HttpStatus.BAD_REQUEST);
        }
        MyUserDetails userDetails = user.map(MyUserDetails::new).get();
        final String jwt_token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt_token,"Successfully Login."));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User responseUser;
        try {
            responseUser = userRepository.save(user);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ErrorResponse(new DBOperationException()), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(responseUser);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object currentLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails = (MyUserDetails) principal;
        return myUserDetails;
    }
}
