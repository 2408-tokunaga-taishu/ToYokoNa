package com.example.ToYokoNA.service;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.repository.TaskRepository;
import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    /*
     * タスク全件取得
     */
    public List<TaskForm> findAllTask() {
        int limit = 1000;
        List<Task> results = taskRepository.findAllByOrderByLimitDateAsc(limit);
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

    public void saveTask(TaskForm taskForm) {
        taskForm.setStatus(1);
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        taskForm.setLimitDate(sdFormat.parse(taskForm.getLimitDate() + " 23:59:59"));
        Task task = setEntity(taskForm);
        taskRepository.save(task);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Task setEntity(TaskForm taskForm) {
        Task task = new Task();
        task.setContent(taskForm.getContent());
        task.setLimitDate(taskForm.getLimitDate());
        task.setStatus(taskForm.getStatus());
        return task;
    }
}