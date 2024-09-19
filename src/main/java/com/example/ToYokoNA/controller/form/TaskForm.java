package com.example.ToYokoNA.controller.form;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TaskForm {
    private int id;
    private String content;
    private int status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}