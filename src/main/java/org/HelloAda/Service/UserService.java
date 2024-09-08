package org.HelloAda.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Entity.Role;
import org.HelloAda.Model.Enum.RolePosition;
import org.HelloAda.Repository.RoleRepository;
import org.HelloAda.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.util.Set;

@Service
@Builder
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public User addUser(UserInput userInput) throws CustomException {
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
    public User updateUser(Long userId, UserInput userInput) throws CustomException {
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(userInput.getEmail());
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        if (userInput.getEmail().isBlank() || userInput.getPassword().isBlank()) {
            throw new CustomException("Empty values are not allowed");
        }
        if (!matcher.matches()) {
            throw new CustomException("Invalid email format: " + userInput.getEmail());
        }
        User existingUser = userRepository.findByEmail(userInput.getEmail());
        if (existingUser != null && !existingUser.getId().equals(userId)) {
            throw new CustomException("Email is already used" + userInput.getEmail());
        }
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        return userRepository.save(user);
    }
    public User getUser(Long userId) throws CustomException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id: " + userId));
    }
    public Long getUserIdByEmail(String username) throws CustomException {
        User user = userRepository.findByEmail(username);
        return user.getId();
    }
    public void deleteUser(Long userId) throws CustomException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id: " + userId));
        userRepository.delete(user);
    }
}