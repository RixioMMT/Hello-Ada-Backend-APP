package org.HelloAda.Resolver;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResolver {
    private final UserService userService;
    @PreAuthorize("hasAuthority('CLIENT')")
    public User addUser(UserInput userInput) throws CustomException {
        return userService.addUser(userInput);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public User updateUser(Long userId, UserInput userInput) throws CustomException {
        return userService.updateUser(userId, userInput);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public User getUser(Long userId) throws CustomException {
        return userService.getUser(userId);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public Long getUserIdByEmail(String userEmail) throws CustomException {
        return userService.getUserIdByEmail(userEmail);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public void deleteUser(Long userId) throws CustomException {
        userService.deleteUser(userId);
    }
}