package org.HelloAda.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.Role;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Enum.RolePosition;
import org.HelloAda.Model.Inputs.LogInInput;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Repository.RoleRepository;
import org.HelloAda.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    public User logIn(LogInInput logInInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInInput.getEmail(),
                        logInInput.getPassword()
                )
        );
        return userRepository.findByEmail(logInInput.getEmail());
    }
    public User signUp(UserInput userInput) throws CustomException {
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(userInput.getEmail());
        if (userInput.getEmail().isBlank() || userInput.getPassword().isBlank()) {
            throw new CustomException("Empty values are not allowed");
        }
        if (!matcher.matches()) {
            throw new CustomException("Invalid email format: " + userInput.getEmail());
        }
        if (userRepository.findByEmail(userInput.getEmail()) != null) {
            throw new CustomException("Email is already used: " + userInput.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRolePosition(RolePosition.CLIENT);
        roles.add(role);
        User user = User.builder()
                .email(userInput.getEmail())
                .password(passwordEncoder.encode(userInput.getPassword()))
                .roles(roles)
                .build();
        return userRepository.save(user);
    }
}