package com.example.ToYokoNA.controller;

import com.example.ToYokoNA.controller.form.TaskForm;
import com.example.ToYokoNA.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ModelAndView deleteTask(@PathVariable int id) {
        //serviceにdeleteByIdメソッドでidと一緒に渡す
        taskService.deleteById(id);
        //リダイレクトでトップページに戻る
        return new ModelAndView("redirect:/");
    }
    /*
     * 編集画面表示
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();
        // 対象の投稿を取得
        TaskForm taskData = taskService.findByIdTask(id);
        // 画面遷移先を指定
        mav.setViewName("/edit");
        // 投稿データオブジェクトを保管
        mav.addObject("tasks", taskData);
        return mav;
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
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return new ModelAndView("redirect:/edit/" + id);
        }
        // 投稿をテーブルに格納
        taskService.saveTask(taskForm);
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