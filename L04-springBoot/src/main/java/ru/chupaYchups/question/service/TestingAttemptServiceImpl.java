package ru.chupaYchups.question.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.config.ApplicationProps;
import ru.chupaYchups.question.config.TestingProps;
import ru.chupaYchups.question.dao.QuestionsDao;
import ru.chupaYchups.question.exception.QuestionException;
import ru.chupaYchups.question.model.Question;
import ru.chupaYchups.question.model.Student;
import ru.chupaYchups.question.model.TestingAttempt;

import java.util.List;

@Service
public class TestingAttemptServiceImpl implements TestingAttemptService {

    private final QuestionsDao questionsDao;
    private final PrintOutputService printOutputService;
    private final InputService inputService;
    private final MessageSource messageSource;
    private final ApplicationProps appProperties;
    private final int quantityToSuccess;

    public static final String HELLO_USER_MSG = "hello" ;
    public static final String SUCCESS_RESULT_MSG = "success_result";
    public static final String FAILED_RESULT_MSG = "failed_result";

    public TestingAttemptServiceImpl(QuestionsDao questionsDao, PrintOutputService printOutputService,
                                     InputService inputService, ApplicationProps applicationProperties,
                                     TestingProps testingProperties, MessageSource messageSource) {
        this.questionsDao = questionsDao;
        this.printOutputService = printOutputService;
        this.inputService = inputService;
        this.quantityToSuccess = testingProperties.getSuccessQty();
        this.messageSource = messageSource;
        this.appProperties = applicationProperties;
    }

    @Override
    public void printAllQuestions() {
        try {
            List<Question> questions = questionsDao.findAllQuestions();
            questions.forEach(q -> printOutputService.print(q.getQuestionString()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new QuestionException("Error while print all questions");
        }
    }

    @Override
    public void doTestingAttempt() {
        try {
            printOutputService.print(messageSource.getMessage(HELLO_USER_MSG, new String[]{}, appProperties.getLocale()));
            Student student = new Student(inputService.getInput());

            List<Question> questions = questionsDao.findAllQuestions();

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
                String.format(
                    messageSource.getMessage(testingAttempt.isSuccess() ? SUCCESS_RESULT_MSG : FAILED_RESULT_MSG,
                        new Object[] {student.getUserName(), testingAttempt.getResult()},
                        appProperties.getLocale())));

        } catch (Exception e) {
            e.printStackTrace();
            throw new QuestionException("Error while do testing attempt");
        }
    }
}
