package com.example.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="schedule") // if table name is similar to model class name then jpa automatically use model class name as table name.
public class Schedule extends BaseEntity{
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String days;
    @Column(name="time_duration")
    private String timeDuration; // if column name is similar to model class name then jpa automatically use model class name as table name.

    public enum Type {
        WEEK,WEEKEND;
    }

    private String status;


}
