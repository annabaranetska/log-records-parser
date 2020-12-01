package com.example.logparser.repository;

import com.example.logparser.model.ApplicationServerLogRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * A class representing the dao for the LogRecord class.
 */
@Repository
public interface ApplicationServerLogRecordRepo extends CrudRepository<ApplicationServerLogRecord, String> {

    @Override
    List<ApplicationServerLogRecord> findAll();

    @Override
    Optional<ApplicationServerLogRecord> findById(@NotNull String id);
}
