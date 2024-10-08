package com.example.ToYokoNA.service;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.repository.TaskRepository;
import com.example.ToYokoNA.repository.entity.Task;
import io.micrometer.common.util.StringUtils;
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
    public List<TaskForm> findAllTask(String startDate, String endDate, String selectStatus, String selectContent) throws ParseException {
//        取得件数の定数
        int limit = 1000;
        List<Task> results = new ArrayList<>();
        SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(startDate)) {
            startDate = startDate.replace("/","-") + " 00:00:00";

        } else {
            startDate = "2020-01-01 00:00:00";
        }
        if (!StringUtils.isEmpty(endDate)) {
            endDate = endDate.replace("/","-") + " 23:59:59";
        } else {
            endDate = "2100-12-31 23:59:59";
        }
        Date start = longDateFormat.parse(startDate);
        Date end = longDateFormat.parse(endDate);
        if (StringUtils.isBlank(selectStatus) && StringUtils.isBlank(selectContent) ) {
            results = taskRepository.findAllByOrderByLimitDateAscLimit(limit, start, end);
        } else if (StringUtils.isBlank(selectStatus)) {
            results = taskRepository.findAllByWHEREContentOrderByLimitDateAsc(limit, start, end, selectContent);
        } else if (StringUtils.isBlank(selectContent)) {
            results = taskRepository.findAllByWHEREStatusOrderByLimitDateAsc(limit, start, end, selectStatus);
        } else {
            results = taskRepository.findAllByWHEREALLOrderByLimitDateAsc(limit, start, end, selectContent, selectStatus);
        }

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

    public void saveTask(TaskForm taskForm) throws ParseException {
        taskForm.setStatus(1);
        Task task = setEntity(taskForm);
        taskRepository.save(task);
    }

    private Task setEntity(TaskForm taskForm) throws ParseException {
        Task task = new Task();
        task.setContent(taskForm.getContent());
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sqlDate =  taskForm.getLimitDate().replace("/", "-");
        task.setLimitDate((sdFormat.parse(sqlDate + " 23:59:59")));
        task.setStatus(taskForm.getStatus());
        return task;
    }

    /*
     * レコード編集
     */
    public void updateTask(TaskForm reqTask) throws ParseException {
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