package com.example.logparser.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
Class representing an application server log record, extending the Record abstract class.
 */
@Entity
public class ApplicationServerLogRecord extends Record implements Serializable {

    @NotNull @NotBlank
    private String typeOfLog;

    @NotNull @NotBlank
    private String hostName;

    public ApplicationServerLogRecord() {
        super();
    }

    /**
     *
     * @param id - id of the record
     * @param duration - duration of the event
     * @param isLong - indicator, if the event is longer than 4 milliseconds
     * @param typeOfLog - type of the server log
     * @param hostName - the name of the host
     */
    public ApplicationServerLogRecord(String id, int duration, boolean isLong, String typeOfLog, String hostName) {
        super(id, duration, isLong);
        this.typeOfLog = typeOfLog;
        this.hostName = hostName;
    }

    public String getTypeOfLog() {
        return typeOfLog;
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public String toString() {
        return "ApplicationServerLogRecord " +
                super.toString() + ", " +
                "typeOfLog=" + typeOfLog +
                ", hostName=" + hostName;
    }
}