package com.example.ToYokoNA.repository;

import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByOrderByLimitDateAsc(@Param("limit") int limit);
}