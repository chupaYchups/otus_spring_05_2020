package ru.chupaYchups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.chupaYchups.question.core.config.ApplicationProperties;
import ru.chupaYchups.question.core.config.TestingProperties;
import ru.chupaYchups.question.core.service.TestingAttemptService;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, TestingProperties.class})
public class L04SpringBootApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(L04SpringBootApplication.class, args);
		TestingAttemptService service = (TestingAttemptService)context.getBean("testingAttemptServiceImpl");
		service.doTestingAttempt();
	}
}
