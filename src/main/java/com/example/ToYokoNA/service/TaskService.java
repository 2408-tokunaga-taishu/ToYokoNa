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
        List<Task> results = taskRepository.findAll();
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            taskForm.setLimitDate(sdf.format(task.getLimitDate()));
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
    /*
     * 編集画面表示時のidをもとにTask検索
     */
    public TaskForm findByIdTask(int id) {
        Task result = taskRepository.findById(id).orElse(null);
        TaskForm task = setTaskForm(result);
        return task;
    }

    private TaskForm setTaskForm(Task result) {
        TaskForm task = new TaskForm();
        task.setId(result.getId());
        task.setContent(result.getContent());
        task.setStatus(result.getStatus());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        task.setLimitDate(sdf.format(result.getLimitDate()));
        return task;
    }

    /*
     * レコード追加・編集
     */
    public void saveTask(TaskForm reqTask) throws ParseException {
        Task saveTask = setTaskEntity(reqTask);
        taskRepository.save(saveTask);
    }
    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setTaskEntity(TaskForm reqTask) throws ParseException {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date formattedDate = sdf.parse(reqTask.getLimitDate());
        task.setLimitDate(formattedDate);
        task.setUpdatedDate(new Date());
        return task;
    }
    /*
     * statusのみ更新処理
     */
    public void saveStatus(TaskForm taskForm) {
        Task task = taskRepository.findById(taskForm.getId()).orElse(null);
        task.setStatus(taskForm.getStatus());
        taskRepository.save(task);
    }
}