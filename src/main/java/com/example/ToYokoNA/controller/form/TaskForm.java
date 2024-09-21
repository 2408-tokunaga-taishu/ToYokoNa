package com.example.ToYokoNA.controller.form;

import com.example.ToYokoNA.Validation.CheckBlank;
import com.example.ToYokoNA.Validation.Past;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Getter
@Setter
public class TaskForm {
    @NotNull
    @NumberFormat(pattern = "^[0-9]*$") //数字のみ許容
    private int id;

    @CheckBlank(groups = ValidGroup1.class)
    @Size(max = 140, message = "タスクは140文字以内で入力してください", groups = ValidGroup1.class)
    private String content;

    private int status;

    @CheckBlank(message = "期限を設定してください", groups = ValidGroup1.class)
    @Pattern(regexp = "^\\d{4}/\\d{1,2}/\\d{1,2}$", groups = ValidGroup1.class, message = "無効な日付です")
    @Past(groups = ValidGroup2.class)
    private String limitDate;

    private Date createdDate;
    private Date updatedDate;
}