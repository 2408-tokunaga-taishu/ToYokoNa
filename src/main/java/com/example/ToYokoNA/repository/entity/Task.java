package com.example.ToYokoNA.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    private int id;
    private String content;
    private int status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}
