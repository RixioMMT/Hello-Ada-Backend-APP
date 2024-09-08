package org.HelloAda.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.Role;
import org.HelloAda.Model.Entity.Task;
import org.HelloAda.Model.Entity.User;
import org.HelloAda.Model.Enum.RolePosition;
import org.HelloAda.Model.Inputs.TaskInput;
import org.HelloAda.Model.Inputs.UserInput;
import org.HelloAda.Repository.TaskRepository;
import org.HelloAda.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Builder
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    public Task addTask(Long userId, TaskInput taskInput) throws CustomException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        Task task = Task.builder()
                .title(taskInput.getTitle())
                .description(taskInput.getDescription())
                .user(user)
                .build();
        return taskRepository.save(task);
    }
    public Task updateTask(Long userId, Long taskId, TaskInput taskInput) throws CustomException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new CustomException("Task not found with id: " + taskId));
        if (!task.getUser().getId().equals(userId)) {
            throw new CustomException("This task does not belong to the user with id: " + userId);
        }
        task.setTitle(taskInput.getTitle());
        task.setDescription(taskInput.getDescription());
        return taskRepository.save(task);
    }
    public Set<Task> getAllTasksByUser(Long userId) throws CustomException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        return taskRepository.findByUserId(userId);
    }
    public void deleteTask(Long userId, Long taskId) throws CustomException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new CustomException("Task not found with id: " + taskId));
        if (!task.getUser().getId().equals(userId)) {
            throw new CustomException("This task does not belong to the user with id: " + userId);
        }
        taskRepository.delete(task);
    }
}
