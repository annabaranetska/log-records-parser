package com.example.logparser.test;

import com.example.logparser.model.ApplicationServerLogRecord;
import com.example.logparser.repository.ApplicationServerLogRecordRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class for testing the ApplicationServerLogRecordRepo storing and retrieving functionality.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServerLogRecordRepoTest {

    @Autowired
    ApplicationServerLogRecordRepo applicationServerLogRecordRepo;


    @Test
    public void givenApplicationServerLogRecordRepository_thenSaveAndRetrieveApplicationServerLogRecord_thenOK(){
        ApplicationServerLogRecord serverLogRecord = applicationServerLogRecordRepo.save(
                new ApplicationServerLogRecord("server-log-test-id", 12, true, "test-type", "test-host"));
        ApplicationServerLogRecord retrievedServerLogRecord = applicationServerLogRecordRepo.findById("server-log-test-id").get();

        Assert.assertNotNull(retrievedServerLogRecord);
        Assert.assertEquals(serverLogRecord.getHostName(), retrievedServerLogRecord.getHostName());
    }
}
