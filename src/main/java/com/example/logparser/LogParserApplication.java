package com.example.logparser;

import com.example.logparser.model.Record;
import com.example.logparser.service.DataInitializer;
import com.example.logparser.service.LogParser;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Starting point of the application. Main method.
 */

@SpringBootApplication
public class LogParserApplication {

	private static Logger logger = Logger.getLogger(LogParserApplication.class.getName());

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(LogParserApplication.class)
				.headless(false).run(args);



		List<Record> records = ctx.getBean(LogParser.class).parseFile(args[0]);

		logger.info("Initializing data of the created Record objects into the database");
		ctx.getBean(DataInitializer.class).initData(records);

	}

}
