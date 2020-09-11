package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.model.Question;
import ru.chupaYchups.question.core.model.Student;
import ru.chupaYchups.question.core.model.TestingAttempt;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class TestingAttemptServiceImpl implements TestingAttemptService {

    private final QuestionsDao questionsDao;
    private final PrintOutputService printOutputService;
    private final InputService inputService;
    private final int quantityToSuccess;

    public static final String HELLO_USER_NAME_REQUEST = "Hello student, what is your name?" ;
    public static final String SUCCESS_RESULT_PATTERN = "Dear %s, test is completed successfully, result : %s";
    public static final String FAILED_RESULT_PATTERN = "Dear %s, test is failed! Your result : %s";

    public TestingAttemptServiceImpl(QuestionsDao questionsDao, PrintOutputService printOutputService, InputService inputService, @Value("${questions.qty.to.success}")int quantityToSuccess) {
        this.questionsDao = questionsDao;
        this.printOutputService = printOutputService;
        this.inputService = inputService;
        this.quantityToSuccess = quantityToSuccess;
    }

    @Override
    public void printAllQuestions() {
        List<Question> questions = questionsDao.findAllQuestion();
        questions.forEach(q -> printOutputService.print(q.getQuestionString()));
    }

    @Override
    public void doTestingAttempt() {

        printOutputService.print(HELLO_USER_NAME_REQUEST);
        Student student = new Student(inputService.getInput());

        List<Question> questions = questionsDao.findAllQuestion();
        TestingAttempt testingAttempt = new TestingAttempt(questions, student, quantityToSuccess);
        while (testingAttempt.isContinued()) {
            Question question = testingAttempt.getNextQuestion();
            printOutputService.print(question.getQuestionString());
            String answer = inputService.getInput();
            if (question.checkAnswer(answer)) {
                testingAttempt.incrementSuccessCounter();
            }
        }

        printOutputService.print(
            String.format(testingAttempt.isSuccess() ? SUCCESS_RESULT_PATTERN : FAILED_RESULT_PATTERN,
                student.getUserName(),
                testingAttempt.getResult()));
    }
}
