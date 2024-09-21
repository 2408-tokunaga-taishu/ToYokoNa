package com.example.ToYokoNA.controller.form;

import com.example.ToYokoNA.Validation.CheckBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Getter
@Setter
public class TaskForm {
    @NotNull
    @NumberFormat(pattern = "^[0-9]*$") //数字のみ許容
    private int id;

    @CheckBlank
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;

    private int status;

    @CheckBlank(message = "期限を設定してください")
    private String limitDate;

    private Date createdDate;
    private Date updatedDate;
}