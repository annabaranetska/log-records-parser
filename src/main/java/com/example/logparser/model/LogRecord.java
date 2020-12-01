package com.example.logparser.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
Class representing a log record, extending the Record abstract class.
 */
@Entity
public class LogRecord extends Record implements Serializable {

    public LogRecord(){
        super();
    }

    public LogRecord(String id, int duration, boolean isLong) {
       super(id, duration, isLong);
    }

    @Override
    public String toString() {
        return "LogRecord " + super.toString();
    }
}