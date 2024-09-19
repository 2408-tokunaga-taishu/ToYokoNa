package com.example.ToYokoNA.controller;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    /*
     * 初期画面表示
     */
    @GetMapping
    public ModelAndView top(Model model) {
    ModelAndView mav = new ModelAndView();
    boolean isShowMessageForm = false;
    List<TaskForm> taskForm = taskService.findAllTask();
    mav.addObject("tasks", taskForm);

    mav.setViewName("/top");
    return mav;
    }
    /*
     * 削除機能
     */
    @DeleteMapping("/delete/{id}")
    //PathVariableでintのidに値が挿入される
    public ModelAndView deleteContent(@PathVariable int id) {
        //serviceにdeleteByIdメソッドでidと一緒に渡す
        taskService.deleteById(id);
        //リダイレクトでトップページに戻る
        return new ModelAndView("redirect:/");
    }
}