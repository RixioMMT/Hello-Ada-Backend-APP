package org.HelloAda.Resolver;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Inputs.LogInInput;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Service.AuthenticationService;
import org.HelloAda.Service.JWTService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationResolver {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    public String logIn(LogInInput logInInput) {
        User authenticatedUser = authenticationService.logIn(logInInput);
        return jwtService.generateToken(authenticatedUser);
    }
    public String signUp(UserInput userInput) throws CustomException {
        User user = authenticationService.signUp(userInput);
        LogInInput logInInput = new LogInInput();
        logInInput.setEmail(user.getEmail());
        logInInput.setPassword(userInput.getPassword());
        User authenticatedUser = authenticationService.logIn(logInInput);
        return jwtService.generateToken(authenticatedUser);
    }
}