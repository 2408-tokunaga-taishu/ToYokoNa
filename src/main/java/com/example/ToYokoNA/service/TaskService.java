package com.example.ToYokoNA.service;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.repository.TaskRepository;
import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.ToYokoNA.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    /*
     * タスク全件取得
     */
    public List<TaskForm> findAllTask() {
        List<Task> results = taskRepository.findALLOrderByLimitDateAsc();
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;
    }

    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (Task task : results) {
            TaskForm taskForm = new TaskForm();
            taskForm.setId(task.getId());
            taskForm.setContent(task.getContent());
            taskForm.setStatus(task.getStatus());
            taskForm.setLimitDate(task.getLimitDate());
            tasks.add(taskForm);
        }
        return tasks;
    }
    /*
     * 削除
     */
    public void deleteById(int id) {
        //JpaRepositoryのdeleteByIdメソッドを使用し、削除してもらう
        taskRepository.deleteById(id);
    }
}
