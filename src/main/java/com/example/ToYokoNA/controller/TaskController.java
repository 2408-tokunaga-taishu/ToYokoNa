package com.example.ToYokoNA.controller;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ModelAndView top(Model model) {
    ModelAndView mav = new ModelAndView();
    List<TaskForm> taskForm = taskService.findAllTask();


    return mav;
    }

}
