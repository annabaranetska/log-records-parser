package com.example.logparser.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
Abstract class representing the Record object,
which will be created based on data from JSON objects in the logfile.txt
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Record implements Serializable {
    @Id @NotBlank @NotNull
    private String id;

    private int duration;

    @NotNull
    private boolean isLong;

    public Record() {
        super();
    }

    /**
     *
     * @param id - id of the record
     * @param duration - duration of the event
     * @param isLong - indicator, if the event is longer than 4 milliseconds
     */
    public Record(String id, int duration, boolean isLong) {
        this.id = id;
        this.duration = duration;
        this.isLong = isLong;
    }

    public String getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isLong() {
        return isLong;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", duration=" + duration +
                ", isLong=" + isLong;
    }
}
