package com.example.logparser.repository;

import com.example.logparser.model.LogRecord;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * A class representing the dao for the ApplicationServerLogRecord class.
 */

@Repository
public interface LogRecordRepo extends CrudRepository<LogRecord, String>{

    @Override
    Optional<LogRecord> findById(@NotNull String id);

    @Override
    List<LogRecord> findAll();
}
