package com.example.ToYokoNA.controller;

import com.example.ToYokoNA.Validation.ValidId;
import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    /*
     * 初期画面表示
     */
    @GetMapping
    public ModelAndView top(Model model, @RequestParam(name = "startDate", required = false) String startDate, @RequestParam(name = "endDate", required = false) String endDate, @RequestParam(name = "selectStatus", required = false) String selectStatus, @RequestParam(name = "selectContent", required = false) String selectContent) {
    ModelAndView mav = new ModelAndView();
    List<TaskForm> results = taskService.findAllTask(startDate, endDate, selectStatus, selectContent);
    mav.addObject("taskForm", results);
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
    public ModelAndView deleteTask(@PathVariable int id) {
        //serviceにdeleteByIdメソッドでidと一緒に渡す
        taskService.deleteById(id);
        //リダイレクトでトップページに戻る
        return new ModelAndView("redirect:/");
    }
    /*
     * 編集画面表示
     */
    @GetMapping({"/edit", "/edit/"})
    public ModelAndView noIdEditTask (RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータです");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable String id, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(id) || (!id.matches("^[0-9]*$"))) {
            redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView();
        // 対象の投稿を取得
        try {
            TaskForm taskData = taskService.findByIdTask(Integer.parseInt(id));
            // 画面遷移先を指定
            mav.setViewName("/edit");
            // 投稿データオブジェクトを保管
            mav.addObject("tasks", taskData);
            return mav;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }
    }
    /*
     * 編集機能
     */
    @PutMapping("/edit/{id}")
    public ModelAndView SaveTask(@PathVariable("id") int id,
                                    @Validated
                                    @ModelAttribute("tasks") TaskForm taskForm,
                                    BindingResult result, RedirectAttributes redirectAttributes) throws ParseException {
        taskForm.setId(id);
        if (result.hasErrors()) {
            // エラーメッセージをRedirectAttributesに追加
            redirectAttributes.addFlashAttribute("errorMessages", result.getAllErrors());
            return new ModelAndView("redirect:/edit/" + id);
        }
        // 投稿をテーブルに格納
        taskService.updateTask(taskForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
    /*
     * ステータス変更処理
     */
    @PutMapping("/{id}")
    public ModelAndView SaveStatus(@PathVariable("id") int id,
                                 @ModelAttribute("status") TaskForm taskForm){
        taskForm.setId(id);
        taskService.saveStatus(taskForm);
        return new ModelAndView("redirect:/");
    }
}