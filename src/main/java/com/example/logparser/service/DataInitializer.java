package com.example.logparser.service;

import com.example.logparser.model.ApplicationServerLogRecord;
import com.example.logparser.model.LogRecord;
import com.example.logparser.model.Record;
import com.example.logparser.repository.ApplicationServerLogRecordRepo;
import com.example.logparser.repository.LogRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * Retrieving the Record objects from the map containing their details, and saving them to the database.
 */

@Component
public class DataInitializer {

    @Autowired
    LogRecordRepo logRecordRepo;

    @Autowired
    ApplicationServerLogRecordRepo applicationServerLogRecordRepo;

    private Logger logger = Logger.getLogger(DataInitializer.class.getName());

    public void initData(List<Record> records){
        logger.info("Saving LogRecord and ApplicationServerLogRecord objects into repositories");
        for (Record record: records){
            if(record.getClass().equals(LogRecord.class)){
                logRecordRepo.save((LogRecord) record);
            } else {
                applicationServerLogRecordRepo.save((ApplicationServerLogRecord) record);
            }
        }
        System.out.println(logRecordRepo.findAll());
    }
}
