package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.model.Question;
import ru.chupaYchups.question.core.model.TestingAttempt;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class TestingAttemptServiceImpl implements TestingAttemptService {

    private final QuestionsDao questionsDao;
    private final PrintOutputService printOutputService;
    private final InputService inputService;

    @Value("${questions.qty.to.success}")
    private int quantityToSuccess;

    private final String SUCCESS_RESULT_PATTERN = "Dear %s, test is completed successfully, result : %d/%d";
    private final String FAILED_RESULT_PATTERN = "Dear %s, test is failed, result : %d/%d";

    public TestingAttemptServiceImpl(QuestionsDao questionsDao, PrintOutputService printOutputService, InputService inputService) {
        this.questionsDao = questionsDao;
        this.printOutputService = printOutputService;
        this.inputService = inputService;
    }

    @Override
    public void printAllQuestions() {
        List<Question> questions = questionsDao.findAllQuestion();
        questions.forEach(q -> printOutputService.print(q.getQuestionString()));
    }

    @Override
    public void doTestingAttempt() {

        final String userName = null;
        boolean testResultOk = false;

        List<Question> questions = questionsDao.findAllQuestion();
        TestingAttempt testingAttempt = new TestingAttempt(questions, userName, quantityToSuccess);

        while (testingAttempt.isContinued()) {
            Question question = testingAttempt.getNextQuestion();
            printOutputService.print(question.getQuestionString());
            String answer = inputService.getInput();
            if (question.checkAnswer(answer)) {
                testingAttempt.incrementSuccessCounter();
            }
        }

        printOutputService.print(testResultOk ? SUCCESS_RESULT_PATTERN : FAILED_RESULT_PATTERN);
    }
}
