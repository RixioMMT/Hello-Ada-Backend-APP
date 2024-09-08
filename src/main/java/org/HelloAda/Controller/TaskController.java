package org.HelloAda.Controller;

import lombok.RequiredArgsConstructor;
import org.HelloAda.Exceptions.CustomException;
import org.HelloAda.Model.Entity.Task;
import org.HelloAda.Model.Inputs.TaskInput;
import org.HelloAda.Resolver.TaskResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskResolver taskResolver;
    @MutationMapping
    public Task addTask(@Argument Long userId, @Argument TaskInput taskInput) throws CustomException {
        return taskResolver.addTask(userId, taskInput);
    }
    @MutationMapping
    public Task updateTask(@Argument TaskInput taskInput, @Argument Long taskId, @Argument Long userId) throws CustomException {
        return taskResolver.updateTask(userId, taskId, taskInput);
    }
    @QueryMapping
    public Set<Task> getAllTasksByUser(@Argument Long userId) throws CustomException {
        return taskResolver.getAllTasksByUser(userId);
    }
    @MutationMapping
    public Boolean deleteTask(@Argument Long userId, @Argument Long taskId) throws CustomException {
        taskResolver.deleteTask(userId, taskId);
        return true;
    }
}