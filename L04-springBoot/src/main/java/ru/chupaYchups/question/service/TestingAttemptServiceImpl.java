package ru.chupaYchups.question.service;

import org.springframework.stereotype.Service;
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
    private final QuestionOutputService questionOutputService;
    private final InputService inputService;
    private final int quantityToSuccess;

    public static final String HELLO_USER_MSG = "hello" ;
    public static final String SUCCESS_RESULT_MSG = "success_result";
    public static final String FAILED_RESULT_MSG = "failed_result";

    public TestingAttemptServiceImpl(QuestionsDao questionsDao, QuestionOutputService questionOutputService,
                                     InputService inputService,
                                     TestingProps testingProperties) {
        this.questionsDao = questionsDao;
        this.questionOutputService = questionOutputService;
        this.inputService = inputService;
        this.quantityToSuccess = testingProperties.getSuccessQty();
    }

    @Override
    public void printAllQuestions() {
        try {
            List<Question> questions = questionsDao.findAllQuestions();
            questions.forEach(q -> questionOutputService.print(q.getQuestionString()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new QuestionException("Error while print all questions");
        }
    }

    @Override
    public void doTestingAttempt() {
        try {
            questionOutputService.printLocalized(HELLO_USER_MSG);
            Student student = new Student(inputService.getInput());

            List<Question> questions = questionsDao.findAllQuestions();

            TestingAttempt testingAttempt = new TestingAttempt(questions, student, quantityToSuccess);
            while (testingAttempt.isContinued()) {
                Question question = testingAttempt.getNextQuestion();
                questionOutputService.print(question.getQuestionString());
                String answer = inputService.getInput();
                if (question.checkAnswer(answer)) {
                    testingAttempt.incrementSuccessCounter();
                }
            }

            questionOutputService.printLocalized(testingAttempt.isSuccess() ? SUCCESS_RESULT_MSG : FAILED_RESULT_MSG,
                new Object[] {student.getUserName(), testingAttempt.getResult()});

        } catch (Exception e) {
            e.printStackTrace();
            throw new QuestionException("Error while do testing attempt");
        }
    }
}
