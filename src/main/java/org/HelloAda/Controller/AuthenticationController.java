package org.HelloAda.Controller;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Inputs.LogInInput;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Resolver.AuthenticationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationResolver authenticationResolver;
    @MutationMapping
    public String logIn(@Argument LogInInput logInInput) {
        return authenticationResolver.logIn(logInInput);
    }
    @MutationMapping
    public String signUp(@Argument UserInput userInput) throws CustomException {
        return authenticationResolver.signUp(userInput);
    }
}