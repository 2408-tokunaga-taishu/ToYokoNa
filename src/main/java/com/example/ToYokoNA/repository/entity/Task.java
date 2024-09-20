package com.example.ToYokoNA.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="status")
    private int status;

    @Column(name="limit_date")
    private Date limitDate;

    @Column(name="created_date", insertable=false, updatable=false)
    private Date createdDate;

    @Column(name="updated_date", insertable=false)
    private Date updatedDate;


}
