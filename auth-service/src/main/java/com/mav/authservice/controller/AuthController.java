package com.mav.authservice.controller;

import com.mav.authservice.config.JwtTokenUtil;
import com.mav.authservice.entity.User;
import com.mav.authservice.model.AuthResponse;
import com.mav.authservice.model.AuthenticationRequest;
import com.mav.authservice.repo.UserRepository;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "auth-controller")
@Log4j2
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    @ApiOperation(value = "Authenticate", notes = "Authenticate a user")
    @Schema(description = "Example JWT token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    public ResponseEntity<?> authenticate(@RequestBody @ApiParam(value = "Authentication Request", required = true) AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = userRepository.findByEmail(request.getEmail());
            log.info("User::" + user);
            AuthResponse authResponse = new AuthResponse();
            if (user != null) {
                authResponse.setToken(jwtTokenUtil.generateToken(user));
                return ResponseEntity.ok(authResponse);
            }
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (InternalAuthenticationServiceException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            // Handle JSON parsing exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validate(@RequestParam  @ApiParam(value = "JWT Token", required = true) String jwtToken)
    {
        boolean isTokenValid = jwtTokenUtil.validateToken(jwtToken);
        return ResponseEntity.ok(isTokenValid);
    }

}
