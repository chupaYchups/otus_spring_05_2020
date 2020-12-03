package ru.chupaYchups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.chupaYchups.question.config.ApplicationProps;
import ru.chupaYchups.question.config.QuestionsFileProps;
import ru.chupaYchups.question.config.TestingProps;
import ru.chupaYchups.question.service.TestingAttemptService;

@SpringBootApplication
public class ApplicationMain {
	public static void main(String[] args) {
		var context = SpringApplication.run(ApplicationMain.class, args);
		TestingAttemptService service = (TestingAttemptService)context.getBean("testingAttemptServiceImpl");
		service.doTestingAttempt();
	}
}
