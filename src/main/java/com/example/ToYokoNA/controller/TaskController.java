package com.example.ToYokoNA.controller;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.repository.entity.Task;
import com.example.ToYokoNA.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Validated
public class TaskController {

    @Autowired
    TaskService taskService;
    /*
     * 初期画面表示
     */
    @GetMapping
    public ModelAndView top(Model model) {
    ModelAndView mav = new ModelAndView();
    boolean isShowTaskForm = false;
    List<TaskForm> taskForm = taskService.findAllTask();
    mav.addObject("tasks", taskForm);
    mav.setViewName("/top");
    return mav;
    }
    /*
     *新規タスク画面
     */
    @GetMapping("/new")
    public ModelAndView newPage(){
        ModelAndView mav = new ModelAndView();
        TaskForm taskForm = new TaskForm();
//        遷移先
        mav.setViewName("/new");
//        空のフォーム送信
        mav.addObject("taskForm", taskForm);
        return mav;
    }

    /*
     * タスク追加処理
     */
    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute("taskForm") @Validated TaskForm taskForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        if (errorMessages.size() >= 1 ) {
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            mav.setViewName("redirect:/new");
        } else {
            taskService.saveTask(taskForm);
            mav.setViewName("redirect:/");
        }
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