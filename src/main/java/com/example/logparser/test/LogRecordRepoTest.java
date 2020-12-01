package com.example.logparser.test;

import org.junit.Assert;
import com.example.logparser.model.LogRecord;
import com.example.logparser.repository.LogRecordRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class for testing the LogRecordRepo storing and retrieving functionality.
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class LogRecordRepoTest {
    @Autowired
    private LogRecordRepo logRecordRepo;

    @Test
    public void givenLogRecordRepository_thenSaveAndRetrieveLogRecord_thenOK(){
        LogRecord logRecord = logRecordRepo.save(new LogRecord("log-test-id", 5, true));
        LogRecord retrievedLogRecord = logRecordRepo.findById("log-test-id").get();
        Assert.assertNotNull(retrievedLogRecord);
        Assert.assertEquals(logRecord.getDuration(), retrievedLogRecord.getDuration());
    }

}
