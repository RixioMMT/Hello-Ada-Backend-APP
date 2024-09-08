package org.HelloAda.Controller;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Resolver.UserResolver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserResolver userResolver;
    @MutationMapping
    public User addUser(@Argument UserInput userInput) throws CustomException {
        return userResolver.addUser(userInput);
    }
    @MutationMapping
    public User updateUser(@Argument UserInput userInput, @Argument Long userId) throws CustomException {
        return userResolver.updateUser(userId, userInput);
    }
    @QueryMapping
    public User getUser(@Argument Long userId) throws CustomException {
        return userResolver.getUser(userId);
    }
    @QueryMapping
    public Long getUserIdByEmail(@Argument String userEmail) throws CustomException {
        return userResolver.getUserIdByEmail(userEmail);
    }
    @MutationMapping
    public Boolean deleteUser(@Argument Long userId) throws CustomException {
        userResolver.deleteUser(userId);
        return true;
    }
}