package org.HelloAda.Repository;

import org.HelloAda.Model.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTitle(Task task);
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    Set<Task> findByUserId(@Param("userId") Long userId);
}