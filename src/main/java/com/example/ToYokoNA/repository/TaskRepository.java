package com.example.ToYokoNA.repository;

import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Integer> {
    public List<Task> findALLOrderBylimitDateAsc();
}
