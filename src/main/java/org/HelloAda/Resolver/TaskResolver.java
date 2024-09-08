package org.HelloAda.Resolver;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.Task;
import org.HelloAda.Model.Inputs.TaskInput;
import org.HelloAda.Service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class TaskResolver {
    private final TaskService taskService;
    @PreAuthorize("hasAuthority('CLIENT')")
    public Task addTask(Long userId, TaskInput taskInput) throws CustomException {
        return taskService.addTask(userId, taskInput);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public Task updateTask(Long userId, Long taskId, TaskInput taskInput) throws CustomException {
        return taskService.updateTask(userId, taskId, taskInput);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public Set<Task>  getAllTasksByUser(Long userId) throws CustomException {
        return taskService.getAllTasksByUser(userId);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    public void deleteTask(Long userId, Long taskId) throws CustomException {
        taskService.deleteTask(userId, taskId);
    }
}