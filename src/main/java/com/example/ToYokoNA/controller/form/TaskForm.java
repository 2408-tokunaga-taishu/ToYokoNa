package com.example.ToYokoNA.controller.form;

import com.example.ToYokoNA.Validation.Past;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
public class TaskForm {
    @NotNull
    private int id;

    @NotBlank(message = "タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;

    private int status;

    @NotNull(message = "期限を設定してください")
    private String limitDate;

    private Date createdDate;
    private Date updatedDate;
    @Past
    @NotBlank(message = "期限を設定してください")
    private String strLimitDate;

}