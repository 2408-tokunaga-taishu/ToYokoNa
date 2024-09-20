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

import static org.apache.logging.log4j.util.Strings.isBlank;


@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    /*
     * タスク全件取得
     */
    public List<TaskForm> findAllTask(String startDate, String endDate, String selectStatus, String selectContent) {
        int limit = 1000;
        List<Task> results = new ArrayList<>();
        if (!isBlank(startDate)) {
            startDate = startDate + " 00:00:00";

        } else {
            startDate = "2020-01-01 00:00:00";
        }
        if (!isBlank(endDate)) {
            endDate = endDate + " 23:59:59";
        } else {
            endDate = "2100-12-31 23:59:59";
        }

            results = taskRepository.findAllByOrderByLimitDateAscLimit(limit, startDate, endDate);

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

    public void saveTask(TaskForm taskForm) throws ParseException {
        taskForm.setStatus(1);
        Task task = setEntity(taskForm);
        taskRepository.save(task);
    }

    private Task setEntity(TaskForm taskForm) throws ParseException {
        Task task = new Task();
        task.setContent(taskForm.getContent());
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        task.setLimitDate((sdFormat.parse(taskForm.getStrLimitDate() + " 23:59:59")));
        task.setStatus(taskForm.getStatus());
        return task;
    }
}